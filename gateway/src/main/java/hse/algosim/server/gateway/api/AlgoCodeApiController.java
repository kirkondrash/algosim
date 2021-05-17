package hse.algosim.server.gateway.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.gateway.scheduling_services.SchedulingManager;
import hse.algosim.server.gateway.service.AlgoCodeApiService;
import hse.algosim.server.gateway.service.RecommendationModelService;
import hse.algosim.server.model.*;
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
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

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
            @ApiParam(value = "user's name of algorithm", required=true) @RequestPart(value="userAlgoName")  String userAlgoName,
            @ApiParam(value = "recommendation models used") @RequestPart(value = "models", required = false) String commaSeparatedModels
    ) {
        UserCodeInfo userCodeInfo;
        HttpStatus responseStatus = HttpStatus.OK;

        try {
            Set<RecommendationModel> modelList = (commaSeparatedModels ==null) ? new HashSet<>() :
                    Arrays.stream(commaSeparatedModels.split(","))
                    .map(recommendationModelService::readModel)
                    .collect(Collectors.toSet());
             userCodeInfo = algoCodeApiService.codeBenchMark(code, userId, userAlgoName, modelList);
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
    public ResponseEntity<String> getRecommendation(String modelName, String algoId) {
        ModelToAlgo modelToAlgo = algoCodeApiService.readModelForAlgo(modelName, algoId);
        String recommendationHost, recommendationPort;
        if (System.getenv("DIND")!= null && System.getenv("DIND").equals("true")){
            recommendationHost=modelToAlgo.getContainerId();
            recommendationPort= modelToAlgo.getModel().getContainerPort();
        } else {
            recommendationHost="localhost";
            recommendationPort=modelToAlgo.getHostPort().toString();
        }

        return ResponseEntity.status(HttpStatus.FOUND).header("Location", String.format("http://%s:%s", recommendationHost, recommendationPort)).build();
    }

    @Override
    public ResponseEntity<Void> registerRecommendation(RecommendationModel model) {
        recommendationModelService.createOrUpdateModel(model);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateRecommendation(String tickValue, String algoId) {
        Set<ModelToAlgo> modelsToAlgo = algoCodeApiService.readModelsForAlgo(algoId);
        HttpClient httpClient = (HttpClient) HttpClient.newBuilder().authenticator(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("API_CLIENT_USER"),System.getenv("API_CLIENT_PASSWORD").toCharArray());
            }
        }).build();
        String recommendationHost, recommendationPort;

        for (ModelToAlgo modelToAlgo: modelsToAlgo){

            if (System.getenv("DIND")!= null && System.getenv("DIND").equals("true")){
                recommendationHost=modelToAlgo.getContainerId();
                recommendationPort= modelToAlgo.getModel().getContainerPort();
            } else {
                recommendationHost="localhost";
                recommendationPort=modelToAlgo.getHostPort().toString();
            }

            try {
                httpClient.send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(String.format("http://%s:%s/%s", recommendationHost, recommendationPort, tickValue)))
                                .POST(HttpRequest.BodyPublishers.noBody())
                                .build(),
                        HttpResponse.BodyHandlers.discarding());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
