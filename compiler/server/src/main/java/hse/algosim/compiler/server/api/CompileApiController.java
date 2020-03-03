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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
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
    public ResponseEntity<Void> compileAlgorithm(@PathVariable("id") UUID id) {
        ApiClient defaultClient = new ApiClient().setBasePath("http://localhost:8000/repo/api");
        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try (ZipInputStream zipIn =
                     new ZipInputStream(new FileInputStream(apiInstance.findAlgorithmCode(id)))) {
            Path p = Paths.get(id.toString()).toAbsolutePath();
            for (ZipEntry ze; (ze = zipIn.getNextEntry()) != null; ) {
                Path resolvedPath = p.resolve(ze.getName());
                if (ze.isDirectory()) {
                    Files.createDirectories(resolvedPath);
                } else {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(zipIn, resolvedPath);
                }
            }

            InvocationRequest request = new DefaultInvocationRequest();
            request.setGoals( Arrays.asList( "clean", "package", "-P package-target") );
            request.setBaseDirectory(p.toFile());
            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
            invoker.execute( request );
            Files.walk(p)
                    .sorted(Comparator.reverseOrder()) //delete file in dir before dir itself
                    .map(Path::toFile)
                    .forEach(File::delete);

        } catch (ApiException | IOException | MavenInvocationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
