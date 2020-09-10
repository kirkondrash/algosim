package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.repo.repository.SrcStatusRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private static final SseEmitter emitter = new SseEmitter(0l);

    @Autowired
    private SrcStatusRepository srcStatusRepository;

    @SneakyThrows
    @Override
    public ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        if (srcStatusRepository.findByAlgoUserId(id).isPresent()){
            throw new ResourceAlreadyExistsException("Status already uploaded for this id");
        }
        srcStatus.setAlgoUserId(id);
        srcStatusRepository.save(srcStatus);
        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event().name(srcStatus.getStatus().toString()).data(id);
        emitter.send(sseEventBuilder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        SrcStatus result = srcStatusRepository.findByAlgoUserId(id).orElseThrow(()-> new ResourceNotFoundException("Status not found for this id"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        srcStatusRepository.findByAlgoUserId(id).orElseThrow(() -> new ResourceNotFoundException("Status not found for this id"));
        srcStatus.setAlgoUserId(id);
        srcStatusRepository.save(srcStatus);
        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event().name(srcStatus.getStatus().toString()).data(id);
        emitter.send(sseEventBuilder);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") String id) {
        srcStatusRepository.findByAlgoUserId(id).orElseThrow(() -> new ResourceNotFoundException("Status not found for this id"));
        srcStatusRepository.deleteByAlgoUserId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray.IdArrayBuilder resBuilder = IdArray.builder();
        srcStatusRepository.findTop10ByStatusEqualsOrderByMetricsDesc(SrcStatus.StatusEnum.SUCCESSFULLY_EXECUTED).forEach(srcStatus -> resBuilder.id(srcStatus.getAlgoUserId()));
        return new ResponseEntity<>(resBuilder.build(), HttpStatus.OK);
    }

    @GetMapping(path = "/stream-sse-mvc", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public SseEmitter streamSseMvc() {
        return emitter;
    }
}
