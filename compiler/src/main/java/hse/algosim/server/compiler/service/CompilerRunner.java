package hse.algosim.server.compiler.service;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
@Slf4j
public class CompilerRunner {

    private final Invoker mavenInvoker = new DefaultInvoker().setMavenExecutable(new File(System.getenv("MAVEN_HOME")));
    private final RepoApiClient repoApiClient;
    private final String frameworkProjectPath;

    @Autowired
    public CompilerRunner(
            RepoApiClient repoApiClient,
            @Value("${framework.project.path}") String frameworkProjectPath) {
        this.repoApiClient = repoApiClient;
        this.frameworkProjectPath = frameworkProjectPath;
    }

    @Async("boundedTaskExecutor")
    public void runCompilation(String id) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        SrcStatus.SrcStatusBuilder srcStatus = SrcStatus.builder();
        String projectDirName = String.format("projects/%s", id);
        File projectDir = new File(projectDirName);
        projectDir.mkdirs();
        try{
            repoApiClient.updateAlgorithmStatus(
                    id,
                    SrcStatus.builder().status(SrcStatus.StatusEnum.COMPILING).build());
            copyFolder(Paths.get(frameworkProjectPath),projectDir.toPath());
            byte[] code = repoApiClient.readAlgorithmCode(id).getBody();
            FileUtils.writeByteArrayToFile(new File(projectDirName + "/src/main/java/TradingAlgorithmImpl.java"), code);

            InvocationRequest mavenInvocationRequest = new DefaultInvocationRequest();
            mavenInvocationRequest.setThreads("2.0C");
            mavenInvocationRequest.setBatchMode(true);
            mavenInvocationRequest.setGoals( Arrays.asList( "clean", "package", "-P package-target") );
            mavenInvocationRequest.setBaseDirectory(projectDir);

            InvocationOutputHandler ioh = s -> printWriter.print(s);
            mavenInvocationRequest.setOutputHandler(ioh);
            mavenInvocationRequest.setErrorHandler(ioh);

            InvocationResult res = mavenInvoker.execute( mavenInvocationRequest );
            if (res.getExitCode() == 0){
                srcStatus.status(SrcStatus.StatusEnum.SUCCESSFULLY_COMPILED);
                try {
                    repoApiClient.createAlgorithmJar(id, new File(projectDirName + "/target/algosim-framework-1.1.0-SNAPSHOT.jar"));
                } catch (FeignException e) {
                    if (e.status() == 409) {
                        repoApiClient.updateAlgorithmJar(id,new File(projectDirName + "/target/algosim-framework-1.1.0-SNAPSHOT.jar"));
                    } else {
                        log.error(e.responseBody().toString());
                        log.error("Exception while uploading {} jar",id,e);
                    }
                }
            }
            else {
                if (res.getExecutionException()!=null){
                    res.getExecutionException().printStackTrace(printWriter);
                }
                 srcStatus
                        .status(SrcStatus.StatusEnum.COMPILATION_FAILED)
                        .errorTrace(stringWriter.toString());
            }
        } catch (MavenInvocationException | FeignException | IOException e) {
            e.printStackTrace(printWriter);
            srcStatus
                    .status(SrcStatus.StatusEnum.COMPILATION_FAILED)
                    .errorTrace(stringWriter.toString());
        } finally {
            try {
                deleteFolder(projectDir.toPath());
                repoApiClient.updateAlgorithmStatus(
                        id,
                        srcStatus.build());
            } catch (FeignException e) {
                log.error(e.responseBody().toString());
                log.error("Exception while setting {} status after compilation: {}",id, srcStatus.build(), e);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void deleteFolder(Path folder) throws IOException {
        Files.walk(folder)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public static void copyFolder(Path src, Path dest) throws IOException {
        Files.walk(src)
                .forEach(source -> copy(source, dest.resolve(src.relativize(source))));
    }

    private static void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
