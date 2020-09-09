package hse.algosim.server.gateway.api;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.gateway.scheduling_services.TaskManager;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.UserCodeInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("${openapi.algosimGateway.base-path:/}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private TaskManager taskManager;
    private RepoApiClientInstance repoApiClient;

    @Autowired
    public AlgoCodeApiController(NativeWebRequest request, RepoApiClientInstance repoApiClient, TaskManager taskManager) {
        this.request = request;
        this.repoApiClient = repoApiClient;
        this.taskManager = taskManager;
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
                    Map<String,Object> node = new HashMap<>();
                    node.put("id",id);
                    node.put("score", status.getMetrics());
                    res.add(node);
                } catch (ApiException ae) {
                    System.out.println(ae.getResponseBody());
                    System.out.println(ae.getCode());
                    ae.printStackTrace();
                }
            });
        } catch (ApiException ae) {
            System.out.println(ae.getResponseBody());
            System.out.println(ae.getCode());
            ae.printStackTrace();
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        try {
            SrcStatus status = repoApiClient.readAlgorithmStatus(id);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ApiException ae) {
            System.out.println(ae.getResponseBody());
            System.out.println(ae.getCode());
            ae.printStackTrace();
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

}
