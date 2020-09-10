package hse.algosim.server.repo.repository;

import hse.algosim.server.model.SrcStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SrcStatusRepository extends CrudRepository<SrcStatus,Long> {

    Optional<SrcStatus> findByAlgoUserId(String algoUserId);

    List<SrcStatus> findTop10ByStatusEqualsOrderByMetricsDesc(SrcStatus.StatusEnum statusEnum);

}
