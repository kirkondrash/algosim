package hse.algosim.server.compiler;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;
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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CompilerServer {

    private final static Invoker mavenInvoker = new DefaultInvoker().setMavenExecutable(new File(System.getenv("MAVEN_EXEC")));

    public static void runCompilation(RepoApiClientInstance repoApiClient, String id, String pathToFramework) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        SrcStatus srcStatus = new SrcStatus();
        String projectDirName = String.format("projects/%s", id);
        File projectDir = new File(projectDirName);
        projectDir.mkdirs();
        try{
            repoApiClient.updateAlgorithmStatus(
                    id,
                    new SrcStatus().status(SrcStatus.StatusEnum.COMPILING));
            copyFolder(Paths.get(pathToFramework),projectDir.toPath());
            System.out.println("ALGO COMPILING");
            Files.move(
                    repoApiClient.readAlgorithmCode(id).toPath(),
                    Paths.get(projectDirName + "/src/main/java/TradingAlgorithmImpl.java"),
                    REPLACE_EXISTING);

            System.out.println("ALGO MOVED");
            InvocationRequest mavenInvocationRequest = new DefaultInvocationRequest();
            mavenInvocationRequest.setThreads("2.0C");
            mavenInvocationRequest.setBatchMode(true);
            mavenInvocationRequest.setGoals( Arrays.asList( "clean", "package", "-P package-target") );
            mavenInvocationRequest.setBaseDirectory(projectDir);

            System.out.println("mavenInvocationRequest SET");

            InvocationOutputHandler ioh = s -> printWriter.print(s);
            mavenInvocationRequest.setOutputHandler(ioh);
            mavenInvocationRequest.setErrorHandler(ioh);

            InvocationResult res = mavenInvoker.execute( mavenInvocationRequest );
            System.out.println("mavenInvocationRequest EXECUTED");
            if (res.getExitCode() == 0){
                System.out.println("SUCCESSFULLY_COMPILED");
                srcStatus.setStatus(SrcStatus.StatusEnum.SUCCESSFULLY_COMPILED);
                try {
                    repoApiClient.createAlgorithmJar(id,new File(projectDirName + "/target/algosim-framework-1.1.0-SNAPSHOT.jar"));
                } catch (ApiException ae) {
                    if (ae.getCode() == 409) {
                        repoApiClient.updateAlgorithmJar(id,new File(projectDirName + "/target/algosim-framework-1.1.0-SNAPSHOT.jar"));
                    } else {
                    System.out.println(ae.getResponseBody());
                }
                }
            }
            else {
                System.out.println("UNSUCCESSFULLY_COMPILED");
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
                repoApiClient.updateAlgorithmStatus(
                        id,
                        srcStatus);
            } catch (ApiException ae) {
                System.out.println(ae.getResponseBody());
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
