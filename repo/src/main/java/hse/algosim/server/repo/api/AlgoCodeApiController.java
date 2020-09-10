package hse.algosim.server.repo.api;

import hse.algosim.server.repo.service.SrcCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final SrcCodeService srcCodeService;
    
    @Autowired
    public AlgoCodeApiController(SrcCodeService srcCodeService) {
        this.srcCodeService = srcCodeService;
    }

    @Override
    public ResponseEntity<Void> createAlgorithmCode(@PathVariable("id") String id, @Valid MultipartFile code) {
        srcCodeService.createAlgorithmCode(id,code);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmCode(@PathVariable("id") String id) {
        File code = srcCodeService.getAlgorithmCodeFile(id);
        Resource r = new PathResource(code.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+ id);
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmCode(@PathVariable("id") String id, @Valid MultipartFile code) {
        srcCodeService.updateAlgorithmStatus(id, code);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmCode(@PathVariable("id") String id) {
        srcCodeService.deleteAlgorithmCode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}