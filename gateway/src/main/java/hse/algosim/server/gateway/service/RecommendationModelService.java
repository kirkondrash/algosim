package hse.algosim.server.gateway.service;

import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.RecommendationModel;
import hse.algosim.server.gateway.repository.RecommendationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RecommendationModelService {

    private final RecommendationModelRepository recommendationModelRepository;

    @Autowired
    public RecommendationModelService(RecommendationModelRepository recommendationModelRepository) {
        this.recommendationModelRepository = recommendationModelRepository;
    }

    @Transactional
    public void createOrUpdateModel(RecommendationModel recommendationModel) {
        recommendationModelRepository.deleteByName(recommendationModel.getName());
        recommendationModelRepository.save(recommendationModel);
    }


    public RecommendationModel readModel(String id) {
        return recommendationModelRepository.findByName(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Model not found for %s", id)));
    }

    public void deleteModel(String id) {
        RecommendationModel recommendationModel = readModel(id);
        recommendationModelRepository.delete(recommendationModel);
    }

}
