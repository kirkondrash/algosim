package hse.algosim.server.gateway.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.gateway.scheduling_services.SchedulingManager;
import hse.algosim.server.gateway.service.AlgoCodeApiService;
import hse.algosim.server.gateway.service.RecommendationModelService;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.RecommendationModel;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${openapi.algosimGateway.base-path:/}")
@Slf4j
public class AlgoCodeApiController implements AlgoCodeApi {

    private final AlgoCodeApiService algoCodeApiService;
    private final RecommendationModelService recommendationModelService;

    @Autowired
    public AlgoCodeApiController(AlgoCodeApiService algoCodeApiService, RecommendationModelService recommendationModelService) {
        this.algoCodeApiService = algoCodeApiService;
        this.recommendationModelService = recommendationModelService;
    }

    @Override
    public ResponseEntity<UserCodeInfo> codeBenchmark(
            @ApiParam(value = "code", required=true) @Valid @RequestPart("code") MultipartFile code,
            @ApiParam(value = "user id", required=true) @RequestPart(value="userId")  String userId,
            @ApiParam(value = "user's name of algorithm", required=true) @RequestPart(value="userAlgoName")  String userAlgoName
    ) {
        UserCodeInfo userCodeInfo;
        HttpStatus responseStatus = HttpStatus.OK;

        try {
             userCodeInfo = algoCodeApiService.codeBenchMark(code, userId, userAlgoName);
        } catch (FeignException e) {
            userCodeInfo = UserCodeInfo.builder().info(e.contentUTF8()).build();
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(userCodeInfo, responseStatus);
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> getTop(){
        return new ResponseEntity<>(algoCodeApiService.getTop(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        return new ResponseEntity<>(algoCodeApiService.readAlgorithmStatus(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getRecommendation(String id) {
        return ResponseEntity.status(HttpStatus.FOUND).header("Location",recommendationModelService.readModel(id).getUrl()).build();
    }

    @Override
    public ResponseEntity<Void> registerRecommendation(RecommendationModel model) {
        recommendationModelService.createOrUpdateModel(model);
        return ResponseEntity.ok().build();
    }
}
