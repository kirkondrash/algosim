package hse.algosim.server.compiler;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CompilerServer {

    private final static Invoker mavenInvoker = new DefaultInvoker().setMavenHome(new File(System.getenv("MAVEN_HOME")));

    public static void runCompilation(RepoApiClientInstance repoApiClient, UUID id) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        SrcStatus srcStatus = new SrcStatus();
        String projectDirName = String.format("projects/%s", id.toString());
        File projectDir = new File(projectDirName);
        projectDir.mkdirs();
        try{
            repoApiClient.replaceAlgorithmStatus(
                    id,
                    new SrcStatus().status(SrcStatus.StatusEnum.COMPILING));
            copyFolder(Paths.get("/framework"),projectDir.toPath());
            Files.move(
                    repoApiClient.getAlgorithmCode(id).toPath(),
                    Paths.get(projectDirName + "/src/main/java/TradingAlgorithmImpl.java"),
                    REPLACE_EXISTING);

            InvocationRequest mavenInvocationRequest = new DefaultInvocationRequest();
            mavenInvocationRequest.setGoals( Arrays.asList( "clean", "package", "-P package-target") );
            mavenInvocationRequest.setBaseDirectory(projectDir);

            InvocationOutputHandler ioh = s -> printWriter.print(s);
            mavenInvocationRequest.setOutputHandler(ioh);
            mavenInvocationRequest.setErrorHandler(ioh);

            InvocationResult res = mavenInvoker.execute( mavenInvocationRequest );
            if (res.getExitCode() == 0){
                srcStatus.setStatus(SrcStatus.StatusEnum.SUCCESSFULLY_COMPILED);
                repoApiClient.uploadAlgorithmJar(id,new File(projectDirName + "/target/algosim-framework-0.0.1.jar"));
            }
            else {
                if (res.getExecutionException()!=null){
                    res.getExecutionException().printStackTrace(printWriter);
                }
                srcStatus = srcStatus.status(SrcStatus.StatusEnum.COMPILATION_FAILED).errorTrace(stringWriter.toString());
            }
        } catch (MavenInvocationException | ApiException | IOException e) {
            e.printStackTrace(printWriter);
            srcStatus = srcStatus.status(SrcStatus.StatusEnum.COMPILATION_FAILED).errorTrace(stringWriter.toString());
        } finally {
            try {
                deleteFolder(projectDir.toPath());
                repoApiClient.replaceAlgorithmStatus(
                        id,
                        srcStatus);
            } catch (ApiException | IOException e) {
                e.printStackTrace();
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
