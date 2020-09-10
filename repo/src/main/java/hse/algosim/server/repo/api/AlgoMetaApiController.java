package hse.algosim.server.repo.api;

import hse.algosim.server.model.SrcMeta;
import hse.algosim.server.repo.service.SrcMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoMetaApiController implements AlgoMetaApi {

    private final SrcMetaService srcMetaService;

    @Autowired
    public AlgoMetaApiController(SrcMetaService srcMetaService) {
        this.srcMetaService = srcMetaService;
    }

    @Override
    public ResponseEntity<Void> createAlgorithmMeta(@PathVariable("id") String id, @RequestBody @Valid SrcMeta srcMeta) {
        srcMetaService.createAlgorithmMeta(id, srcMeta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcMeta> readAlgorithmMeta(@PathVariable("id") String id) {
        SrcMeta result = srcMetaService.readAlgorithmMeta(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmMeta(@PathVariable("id") String id, @Valid SrcMeta srcMeta) {
        srcMetaService.updateAlgorithmStatus(id, srcMeta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmMeta(@PathVariable("id") String id) {
        srcMetaService.deleteAlgorithmMeta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
