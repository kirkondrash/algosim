package hse.algosim.server.repo.service;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.repo.repository.SrcStatusRepository;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public class SrcStatusService {

    private final SrcStatusRepository srcStatusRepository;
    @Getter
    private final SseEmitter emitter = new SseEmitter(0l);


    @Autowired
    public SrcStatusService(SrcStatusRepository srcStatusRepository) {
        this.srcStatusRepository = srcStatusRepository;
    }


    public void createAlgorithmStatus(String id, SrcStatus srcStatus) {
        srcStatusRepository
                .findByAlgoUserId(id)
                .ifPresent(prevStatus -> {throw new ResourceAlreadyExistsException(String.format("Status %s already uploaded for id %s", prevStatus.toString(), prevStatus.getAlgoUserId()));});
        srcStatusRepository.save(srcStatus.toBuilder().algoUserId(id).build());
        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event().name(srcStatus.getStatus().toString()).data(id);
        try {
            emitter.send(sseEventBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SrcStatus readAlgorithmStatus(String id) {
        SrcStatus result = srcStatusRepository.findByAlgoUserId(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Status not found for id %s", id)));
        return result;
    }

    @SneakyThrows
    public void updateAlgorithmStatus(String id, SrcStatus srcStatus) {
        deleteAlgorithmStatus(id);
        createAlgorithmStatus(id, srcStatus);
    }

    public void deleteAlgorithmStatus(String id) {
        SrcStatus srcStatus = readAlgorithmStatus(id);
        srcStatusRepository.delete(srcStatus);
    }

    public IdArray getTopCode() {
        IdArray.IdArrayBuilder resBuilder = IdArray.builder();
        srcStatusRepository.findTop10ByStatusEqualsOrderByMetricsDesc(SrcStatus.StatusEnum.SUCCESSFULLY_EXECUTED).forEach(srcStatus -> resBuilder.id(srcStatus.getAlgoUserId()));
        return resBuilder.build();
    }

}
