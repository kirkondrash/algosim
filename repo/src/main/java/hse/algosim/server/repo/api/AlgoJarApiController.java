package hse.algosim.server.repo.api;

import hse.algosim.server.repo.service.SrcJarService;
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
public class AlgoJarApiController implements AlgoJarApi {

    private final SrcJarService srcJarService;

    @Autowired
    public AlgoJarApiController(SrcJarService srcJarService) {
        this.srcJarService = srcJarService;
    }

    @Override
    public ResponseEntity<Void> createAlgorithmJar(@PathVariable("id") String id, @Valid MultipartFile jar) {
        srcJarService.createAlgorithmJar(id,jar);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmJar(@PathVariable("id") String id) {
        File jar = srcJarService.getAlgorithmJarFile(id);
        Resource r = new PathResource(jar.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+ id);
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmJar(@PathVariable("id") String id, @Valid MultipartFile jar) {
        srcJarService.updateAlgorithmStatus(id,jar);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmJar(@PathVariable("id") String id) {
        srcJarService.deleteAlgorithmJar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}