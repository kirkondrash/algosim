package hse.algosim.gateway.server.api;

import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.RepoApiClientInstance;
import hse.algosim.repo.client.model.IdArray;
import hse.algosim.repo.client.model.SrcStatus;
import hse.algosim.repo.client.model.SrcStatus.StatusEnum;
import hse.algosim.compiler.client.api.CompilerApiClientInstance;
import hse.algosim.executor.client.api.ExecutorApiClientInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("${openapi.algosimGateway.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new hse.algosim.repo.client.api.ApiClient().setBasePath("http://localhost:8000/repo/api"));
    private final static CompilerApiClientInstance compilerApiClient = new CompilerApiClientInstance(new hse.algosim.compiler.client.api.ApiClient().setBasePath("http://localhost:8000/compiler/api"));
    private final static ExecutorApiClientInstance executorApiClient = new ExecutorApiClientInstance(new hse.algosim.executor.client.api.ApiClient().setBasePath("http://localhost:8000/executor/api"));

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoCodeApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Map<String,String>> getAlgorithmCode(@Valid MultipartFile code) {
        UUID id = UUID.randomUUID(); // UUID | UUID of algorithm to fetch
        File f = new File(id.toString()).getAbsoluteFile();
        try {
            code.transferTo(f);
            System.out.println("Starting demo scenario for id "+id.toString());
            repoApiClient.uploadAlgorithmCode(id,f);
            repoApiClient.uploadAlgorithmStatus(id, new SrcStatus().status(StatusEnum.SCHEDULED_FOR_COMPILATION));
            f.delete();
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }

        CompletableFuture<Void> demo = CompletableFuture.runAsync(()-> {
            try {
                while (true) {
                    try {
                        compilerApiClient.compileAlgorithm(id);
                        break;
                    } catch (hse.algosim.compiler.client.api.ApiException ae) {
                        System.out.println("Compiler busy!");
                        Thread.sleep(2000);
                    }
                }
                SrcStatus srcStatus;
                do {
                    srcStatus = repoApiClient.getAlgorithmStatus(id);
                    Thread.sleep(2000);
                    System.out.println(id.toString() + " is compiling!");
                } while (srcStatus.getStatus().compareTo(StatusEnum.COMPILING) == 0);
                System.out.println(srcStatus.toString());
                if (srcStatus.getStatus().compareTo(StatusEnum.SUCCESSFULLY_COMPILED)==0){
                    executorApiClient.executeAlgorithm(id);
                    do {
                        srcStatus = repoApiClient.getAlgorithmStatus(id);
                        Thread.sleep(2000);
                        System.out.println(id.toString() + " is executing!");
                    } while (srcStatus.getStatus().compareTo(StatusEnum.EXECUTING) == 0);
                    System.out.println(srcStatus.toString());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        Map<String,String> res = new HashMap<>();
        res.put("id",id.toString());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String,String>> getTop(){
        Map<String,String> res = new HashMap<>();
        try {
            IdArray ids = repoApiClient.getTopCode();
            ids.getId().forEach( id -> {
                try {
                    File receivedFile = repoApiClient.getAlgorithmCode(UUID.fromString(id));
                    String firstLine = Files.readAllLines(receivedFile.toPath()).get(0);
                    System.out.println(firstLine);
                    res.put(id,firstLine);
                } catch (ApiException | IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
