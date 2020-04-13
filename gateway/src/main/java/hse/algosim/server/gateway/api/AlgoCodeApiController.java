package hse.algosim.server.gateway.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.compiler.api.CompilerApiClientInstance;
import hse.algosim.client.executor.api.ExecutorApiClientInstance;
import hse.algosim.client.model.IdArray;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.gateway.queues.TaskManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("${openapi.algosimGateway.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private static final ObjectMapper mapper = new ObjectMapper();

    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/repo/api"));
    private final static CompilerApiClientInstance compilerApiClient = new CompilerApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/compiler/api"));
    private final static ExecutorApiClientInstance executorApiClient = new ExecutorApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/executor/api"));
    private final static TaskManager taskManager = new TaskManager(repoApiClient,compilerApiClient,executorApiClient);

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
        Map<String,String> res = new HashMap<>();
        res.put("id",id.toString());
        try {
            taskManager.scheduleCodeAnalysis(id, code);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("error",e.getMessage());
        }

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> getTop(){
        List<Map<String,Object>> res = new ArrayList<>();
        try {
            IdArray ids = repoApiClient.getTopCode();
            ids.getId().forEach( id -> {
                try {
                    SrcStatus status = repoApiClient.getAlgorithmStatus(UUID.fromString(id));
                    System.out.println(status.toString());
                    JsonNode winLossNode = mapper.readTree(status.getMetrics()).get("winloss");
                    Map<String,Object> node = new HashMap<>();
                    node.put("id",id);
                    node.put("score", winLossNode.asDouble());
                    res.add(node);
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
