package hse.algosim.server.compiler;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;
import org.apache.maven.shared.invoker.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CompilerServer {

    private final static Invoker mavenInvoker = new DefaultInvoker().setMavenExecutable(new File(System.getenv("MAVEN_EXEC")));
    private final static ProcessBuilder pb = new ProcessBuilder();

    public static void runCompilation(RepoApiClientInstance repoApiClient, String id, String pathToFramework) {
        SrcStatus srcStatus = null;
        String projectDirName = String.format("projects/%s", id);
        File projectDir = new File(projectDirName);
        projectDir.mkdirs();
        try{
            repoApiClient.updateAlgorithmStatus(
                    id,
                    new SrcStatus().status(SrcStatus.StatusEnum.COMPILING));
            copyFolder(Paths.get(pathToFramework),projectDir.toPath());
            Files.move(
                    repoApiClient.readAlgorithmCode(id).toPath(),
                    Paths.get(projectDirName + "/src/main/java/TradingAlgorithmImpl.java"),
                    REPLACE_EXISTING);

            Process p = pb
                    .command(Arrays.asList(
                            "/app/mvnw",
                            "clean",
                            "package",
                            "-P package-target"))
                    .directory(projectDir)
                    .redirectErrorStream(true)
                    .start();
            try (BufferedReader pOutputReader = new BufferedReader(new InputStreamReader(p.getInputStream()))){

                srcStatus = new SrcStatus()
                        .status(SrcStatus.StatusEnum.COMPILATION_FAILED)
                        .errorTrace(pOutputReader.lines().collect(Collectors.joining(System.lineSeparator())));

                if (p.waitFor() == 0) {
                    srcStatus = new SrcStatus().status(SrcStatus.StatusEnum.SUCCESSFULLY_COMPILED);
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
            }
            
        } catch (InterruptedException | ApiException | IOException e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
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
