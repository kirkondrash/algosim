package hse.algosim.server.repo.repository;

import hse.algosim.server.model.SrcJar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SrcJarRepository extends CrudRepository<SrcJar,Long> {
    Optional<SrcJar> findByAlgoUserId(String algoUserId);
}
