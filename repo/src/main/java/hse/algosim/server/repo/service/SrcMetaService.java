package hse.algosim.server.repo.service;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.SrcMeta;
import hse.algosim.server.repo.repository.SrcMetaRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrcMetaService {

    private final SrcMetaRepository srcMetaRepository;

    @Autowired
    public SrcMetaService(SrcMetaRepository srcMetaRepository) {
        this.srcMetaRepository = srcMetaRepository;
    }

    @SneakyThrows
    public void createAlgorithmMeta(String id, SrcMeta srcMeta) {
        srcMetaRepository
                .findByAlgoUserId(id)
                .ifPresent(prevMeta -> {throw new ResourceAlreadyExistsException(String.format("Meta %s already uploaded for this id", prevMeta.toString(), prevMeta.getAlgoUserId()));});

        srcMetaRepository.save(srcMeta.toBuilder().algoUserId(id).build());
    }


    public SrcMeta readAlgorithmMeta(String id) {
        SrcMeta result = srcMetaRepository.findByAlgoUserId(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Meta not found for %s", id)));
        return result;
    }

    @SneakyThrows
    public void updateAlgorithmMeta(String id, SrcMeta srcMeta) {
        deleteAlgorithmMeta(id);
        createAlgorithmMeta(id, srcMeta);
    }

    public void deleteAlgorithmMeta(String id) {
        SrcMeta srcMeta = readAlgorithmMeta(id);
        srcMetaRepository.delete(srcMeta);
    }

}
