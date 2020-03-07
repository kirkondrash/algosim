package hse.algosim.compiler.server.api;

import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.DefaultApi;
import org.apache.maven.shared.invoker.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("${openapi.algosimCompiler.base-path:/api}")
public class CompileApiController implements CompileApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CompileApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Map<String,String>> compileAlgorithm(@PathVariable("id") UUID id) {
        ApiClient defaultClient = new ApiClient().setBasePath("http://localhost:8000/repo/api");
        DefaultApi apiInstance = new DefaultApi(defaultClient);
        StringWriter stringWriter = new StringWriter();
        try {
            apiInstance.findAlgorithmCode(id).renameTo(new File("/framework/src/main/java/TradingAlgorithmImpl.java"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));

            InvocationRequest request = new DefaultInvocationRequest();
            request.setGoals( Arrays.asList( "clean", "package", "-P package-target") );
            request.setBaseDirectory(new File("/framework"));

            InvocationOutputHandler ioh = new InvocationOutputHandler() {
                @Override
                public void consumeLine(String s) throws IOException {
                    stringWriter.write(s+System.lineSeparator());
                }
            };
            request.setOutputHandler(ioh);
            request.setErrorHandler(ioh);

            InvocationResult res = invoker.execute( request );
            if (res.getExecutionException()!=null){
                res.getExecutionException().printStackTrace(new PrintWriter(stringWriter));
            }
        } catch (MavenInvocationException | ApiException e) {
            e.printStackTrace(new PrintWriter(stringWriter));
        }
        Map<String,String> res = new HashMap<>();
        res.put("result",stringWriter.toString());
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }
}
