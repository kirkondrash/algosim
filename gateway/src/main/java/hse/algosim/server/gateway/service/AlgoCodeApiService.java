package hse.algosim.server.gateway.service;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.gateway.scheduling_services.SchedulingManager;
import hse.algosim.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AlgoCodeApiService {

    private final SchedulingManager schedulingManager;
    private final RepoApiClient repoApiClient;

    @Autowired
    public AlgoCodeApiService(SchedulingManager schedulingManager, RepoApiClient repoApiClient) {
        this.schedulingManager = schedulingManager;
        this.repoApiClient = repoApiClient;
    }

    public UserCodeInfo codeBenchMark(MultipartFile code, String userId, String userAlgoName, Set<RecommendationModel> modelList) throws FeignException {
        String codeId = String.format("%s_%s",userId,userAlgoName);
        UserCodeInfo.UserCodeInfoBuilder userCodeInfo = UserCodeInfo.builder().id(codeId).info("Successfully uploaded");
        schedulingManager.scheduleCodeAnalysis(codeId, code, modelList);
        return userCodeInfo.build();
    }

    public List<Map<String,Object>> getTop(){
        List<Map<String,Object>> res = new ArrayList<>();
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
                log.error("",e);
            }
        });
        return res;
    }

    public SrcStatus readAlgorithmStatus(String id) {
        try {
            SrcStatus status = repoApiClient.readAlgorithmStatus(id).getBody();
            return status;
        } catch (FeignException e) {
            log.error(e.responseBody().toString());
            log.error("Exception while trying to read {} status from repo server", id, e);
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    public Set<ModelToAlgo> readModelsForAlgo(String algoId) {
        return repoApiClient.readAlgorithmMeta(algoId).getBody()
                .getModels()
                .stream()
                .collect(Collectors.toSet());
    }

    public ModelToAlgo readModelForAlgo(String modelName, String algoId) {
        return repoApiClient.readAlgorithmMeta(algoId).getBody()
                .getModels()
                .stream()
                .filter(m -> m.getModel().getName().equals(modelName))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Model %s not found for algo %s", modelName, algoId)));
    }
}
