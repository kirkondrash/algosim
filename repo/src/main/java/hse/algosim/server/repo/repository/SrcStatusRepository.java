package hse.algosim.server.repo.repository;

import hse.algosim.server.model.SrcStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SrcStatusRepository extends CrudRepository<SrcStatus,Long> {

    Optional<SrcStatus> findByAlgoUserId(String algoUserId);

    Iterable<SrcStatus> findTop10ByStatusEqualsOrderByMetricsDesc(SrcStatus.StatusEnum statusEnum);

    void deleteByAlgoUserId(String algoUserId);
}
