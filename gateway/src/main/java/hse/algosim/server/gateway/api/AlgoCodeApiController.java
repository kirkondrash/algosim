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
import hse.algosim.server.model.UserCodeInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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

    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new ApiClient().setBasePath(System.getProperty("repoUrl","http://repo:8080/api")).setUsername("user").setPassword("password"));
    private final static CompilerApiClientInstance compilerApiClient = new CompilerApiClientInstance(new ApiClient().setBasePath(System.getProperty("compilerUrl","http://compiler:8080/api")).setUsername("user").setPassword("password"));
    private final static ExecutorApiClientInstance executorApiClient = new ExecutorApiClientInstance(new ApiClient().setBasePath(System.getProperty("executorUrl","http://executor:8080/api")).setUsername("user").setPassword("password"));
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
    public ResponseEntity<UserCodeInfo> codeBenchmark(
            @ApiParam(value = "code", required=true) @Valid @RequestPart("code") MultipartFile code,
            @ApiParam(value = "user id", required=true) @RequestPart(value="userId")  String userId,
            @ApiParam(value = "user's name of algorithm", required=true) @RequestPart(value="userAlgoName")  String userAlgoName
    ) {
        String codeId = String.format("%s_%s",userId,userAlgoName);
        HttpStatus responseStatus = HttpStatus.OK;
        UserCodeInfo userCodeInfo = new UserCodeInfo().id(codeId).info("Successfully uploaded");
        try {
            taskManager.scheduleCodeAnalysis(codeId, code);
        } catch (ApiException e) {
            userCodeInfo.setInfo(e.getResponseBody());
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(userCodeInfo, responseStatus);
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> getTop(){
        List<Map<String,Object>> res = new ArrayList<>();
        try {
            IdArray ids = repoApiClient.getTopCode();
            ids.getId().forEach( id -> {
                try {
                    SrcStatus status = repoApiClient.readAlgorithmStatus(id);
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
