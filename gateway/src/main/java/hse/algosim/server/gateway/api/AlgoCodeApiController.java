package hse.algosim.server.gateway.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.gateway.scheduling_services.SchedulingManager;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.UserCodeInfo;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private SchedulingManager schedulingManager;
    private RepoApiClient repoApiClient;

    @Autowired
    public AlgoCodeApiController(NativeWebRequest request, RepoApiClient repoApiClient, SchedulingManager schedulingManager) {
        this.request = request;
        this.repoApiClient = repoApiClient;
        this.schedulingManager = schedulingManager;
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
        UserCodeInfo userCodeInfo = UserCodeInfo.builder().id(codeId).info("Successfully uploaded").build();
        try {
            schedulingManager.scheduleCodeAnalysis(codeId, code);
        } catch (FeignException e) {
            userCodeInfo.setInfo(e.contentUTF8());
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(userCodeInfo, responseStatus);
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> getTop(){
        List<Map<String,Object>> res = new ArrayList<>();
        try {
            IdArray ids = repoApiClient.getTopCode().getBody();
            ids.getId().forEach( id -> {
                try {
                    SrcStatus status = repoApiClient.readAlgorithmStatus(id).getBody();
                    Map<String,Object> node = new HashMap<>();
                    node.put("id",id);
                    node.put("score", status.getMetrics());
                    res.add(node);
                } catch (FeignException e) {
                    log.error(e.responseBody().toString());
                    log.error("{}",e.getCause());
                }
            });
        } catch (FeignException e) {
            log.error(e.responseBody().toString());
            log.error("{}",e.getCause());
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        try {
            SrcStatus status = repoApiClient.readAlgorithmStatus(id).getBody();
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (FeignException e) {
            log.error(e.responseBody().toString());
            log.error("{}",e.getCause());
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

}
