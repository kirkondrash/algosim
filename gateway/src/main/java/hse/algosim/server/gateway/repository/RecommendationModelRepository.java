package hse.algosim.server.gateway.repository;

import hse.algosim.server.model.RecommendationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationModelRepository extends CrudRepository<RecommendationModel,Long> {
    Optional<RecommendationModel> findByName(String name);
    void deleteByName(String name);
}
