package hse.algosim.server.repo.repository;

import hse.algosim.server.model.SrcCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SrcCodeRepository extends CrudRepository<SrcCode,Long> {
    Optional<SrcCode> findByAlgoUserId(String algoUserId);
}
