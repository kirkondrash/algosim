package hse.algosim.compiler.server.api;

import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.api.repo.RepoApiClientInstance;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.model.SrcStatus.StatusEnum;
import org.apache.maven.shared.invoker.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Controller
@RequestMapping("${openapi.algosimCompiler.base-path:/api}")
public class CompilerApiController implements CompilerApi {

    private final NativeWebRequest request;
    private final static Invoker mavenInvoker = new DefaultInvoker().setMavenHome(new File(System.getenv("MAVEN_HOME")));
    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/repo/api"));
    private final static ThreadPoolExecutor singleThreadExecutor = new ThreadPoolExecutor(
            1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2));


    @org.springframework.beans.factory.annotation.Autowired
    public CompilerApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> compileAlgorithm(@PathVariable("id") UUID id) {
        try {
            singleThreadExecutor.submit(()->{
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                SrcStatus srcStatus = new SrcStatus();
                String projectDirName = String.format("projects/%s", id.toString());
                File projectDir = new File(projectDirName);
                projectDir.mkdirs();
                try{
                    repoApiClient.replaceAlgorithmStatus(
                            id,
                            new SrcStatus().status(StatusEnum.COMPILING));
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
                        srcStatus.setStatus(StatusEnum.SUCCESSFULLY_COMPILED);
                        repoApiClient.uploadAlgorithmJar(id,new File(projectDirName + "/target/algosim-framework-0.0.1.jar"));
                    }
                    else {
                        if (res.getExecutionException()!=null){
                            res.getExecutionException().printStackTrace(printWriter);
                        }
                        srcStatus = srcStatus.status(StatusEnum.COMPILATION_FAILED).errorTrace(stringWriter.toString());
                    }
                } catch (MavenInvocationException | ApiException | IOException e) {
                    e.printStackTrace(printWriter);
                    srcStatus = srcStatus.status(StatusEnum.COMPILATION_FAILED).errorTrace(stringWriter.toString());
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

            });
        }catch (RejectedExecutionException re){
            System.out.println("Caught RejectedExecutionException" + id.toString());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> getReadiness() {
        if (singleThreadExecutor.getQueue().size()<2)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public void deleteFolder(Path folder) throws IOException {
        Files.walk(folder)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public  void copyFolder(Path src, Path dest) throws IOException {
        Files.walk(src)
                .forEach(source -> copy(source, dest.resolve(src.relativize(source))));
    }

    private void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
