package hse.algosim.gateway.server.api;

import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.api.compiler.CompilerApiClientInstance;
import hse.algosim.client.api.executor.ExecutorApiClientInstance;
import hse.algosim.client.api.repo.RepoApiClientInstance;
import hse.algosim.client.model.IdArray;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.model.SrcStatus.StatusEnum;
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
    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/repo/api"));
    private final static CompilerApiClientInstance compilerApiClient = new CompilerApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/compiler/api"));
    private final static ExecutorApiClientInstance executorApiClient = new ExecutorApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/executor/api"));

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
                    } catch (ApiException ae) {
                        System.out.println("Compiler busy!");
                        Thread.sleep(2000);
                    }
                }
                SrcStatus srcStatus;
                do {
                    srcStatus = repoApiClient.getAlgorithmStatus(id);
                    Thread.sleep(2000);
                    System.out.println(String.format("%s was taken by worker for compilation! State: %s",id.toString(), srcStatus.getStatus()));
                } while (srcStatus.getStatus().compareTo(StatusEnum.COMPILING) == 0
                        || srcStatus.getStatus().compareTo(StatusEnum.SCHEDULED_FOR_COMPILATION) == 0 );
                System.out.println(srcStatus.toString());
                if (srcStatus.getStatus().compareTo(StatusEnum.SUCCESSFULLY_COMPILED)==0){
                    repoApiClient.replaceAlgorithmStatus(id, srcStatus.status(StatusEnum.SCHEDULED_FOR_EXECUTION));
                    while (true) {
                        try {
                            executorApiClient.executeAlgorithm(id);
                            break;
                        } catch (ApiException ae) {
                            System.out.println("Executor busy!");
                            Thread.sleep(2000);
                        }
                    }
                    do {
                        srcStatus = repoApiClient.getAlgorithmStatus(id);
                        Thread.sleep(2000);
                        System.out.println(String.format("%s was taken by worker for execution! State: %s",id.toString(), srcStatus.getStatus()));
                    } while (srcStatus.getStatus().compareTo(StatusEnum.EXECUTING) == 0
                            || srcStatus.getStatus().compareTo(StatusEnum.SCHEDULED_FOR_EXECUTION) == 0 );
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
