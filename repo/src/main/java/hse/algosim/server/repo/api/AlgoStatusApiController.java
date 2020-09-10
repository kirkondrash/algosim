package hse.algosim.server.repo.api;

import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.repo.service.SrcStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final SrcStatusService srcStatusService;

    @Autowired
    public AlgoStatusApiController(SrcStatusService srcStatusService) {
        this.srcStatusService = srcStatusService;
    }

    @Override
    public ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        srcStatusService.createAlgorithmStatus(id,srcStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        SrcStatus result = srcStatusService.readAlgorithmStatus(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        srcStatusService.updateAlgorithmStatus(id, srcStatus);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") String id) {
        srcStatusService.deleteAlgorithmStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray result = srcStatusService.getTopCode();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public SseEmitter statusUpdateSSE() {
        return srcStatusService.getEmitter();
    }
}
