package hse.algosim.server.repo.repository;

import hse.algosim.server.model.SrcMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SrcMetaRepository extends CrudRepository<SrcMeta,Long> {
    Optional<SrcMeta> findByAlgoUserId(String algoUserId);
}
