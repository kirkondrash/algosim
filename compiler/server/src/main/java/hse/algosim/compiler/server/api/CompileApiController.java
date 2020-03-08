package hse.algosim.compiler.server.api;

import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.RepoApi;
import hse.algosim.repo.client.model.SrcStatus;
import hse.algosim.repo.client.model.SrcStatus.StatusEnum;
import org.apache.maven.shared.invoker.*;
import org.springframework.http.HttpHeaders;
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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("${openapi.algosimCompiler.base-path:/api}")
public class CompileApiController implements CompileApi {

    private final NativeWebRequest request;
    private Invoker mavenInvoker;


    @org.springframework.beans.factory.annotation.Autowired
    public CompileApiController(NativeWebRequest request) {
        this.request = request;
        mavenInvoker = new DefaultInvoker();
        mavenInvoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> compileAlgorithm(@PathVariable("id") UUID id) {
        ApiClient defaultClient = new ApiClient().setBasePath("http://localhost:8000/repo/api");
        RepoApi apiInstance = new RepoApi(defaultClient);

        try {
            apiInstance.replaceAlgorithmStatus(
                    id,
                    new SrcStatus().status(StatusEnum.COMPILING));
            Files.move(
                    apiInstance.getAlgorithmCode(id).toPath(),
                    Paths.get("/framework/src/main/java/TradingAlgorithmImpl.java"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (ApiException e) {
            e.printStackTrace();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.putAll(e.getResponseHeaders());
            return new ResponseEntity<>(
                    httpHeaders,
                    HttpStatus.valueOf(e.getCode()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompletableFuture<Void> compilation = CompletableFuture.runAsync(()->{
            StringWriter stringWriter = new StringWriter();
            StatusEnum status = StatusEnum.COMPILATION_FAILED;
            try{
                InvocationRequest mavenInvocationRequest = new DefaultInvocationRequest();
                mavenInvocationRequest.setGoals( Arrays.asList( "clean", "package", "-P package-target") );
                mavenInvocationRequest.setBaseDirectory(new File("/framework"));

                InvocationOutputHandler ioh = s -> stringWriter.write(s+System.lineSeparator());
                mavenInvocationRequest.setOutputHandler(ioh);
                mavenInvocationRequest.setErrorHandler(ioh);

                InvocationResult res = mavenInvoker.execute( mavenInvocationRequest );
                if (res.getExitCode() == 0){
                    status = StatusEnum.SUCCESSFULLY_COMPILED;
                }
                else {
                    if (res.getExecutionException()!=null){
                        res.getExecutionException().printStackTrace(new PrintWriter(stringWriter));
                    }
                }
            } catch (MavenInvocationException e) {
                e.printStackTrace(new PrintWriter(stringWriter));
            } finally {
                SrcStatus srcStatus = new SrcStatus().status(status).errorTrace(stringWriter.toString());
                try {
                    if (status.compareTo(StatusEnum.SUCCESSFULLY_COMPILED)==0) {
                        apiInstance.uploadAlgorithmJar(id,new File("/framework/target/algosim-framework-0.0.1.jar"));
                        srcStatus.setErrorTrace(null);
                    }
                    apiInstance.replaceAlgorithmStatus(
                            id,
                            srcStatus);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }

        });

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
