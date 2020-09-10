package hse.algosim.client.repo.api;

import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcMeta;
import hse.algosim.server.model.SrcStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@FeignClient(name="repo-client", url = "${repo.basePath}")
public interface RepoApiClient {

    @GetMapping("/getTopCode")
    ResponseEntity<IdArray> getTopCode();


    @PostMapping("/algoStatus/{id}")
    ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") String id, @RequestBody SrcStatus srcStatus);

    @GetMapping("/algoStatus/{id}")
    ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id);

    @PutMapping("/algoStatus/{id}")
    ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") String id, @RequestBody SrcStatus srcStatus);

    @DeleteMapping("/algoStatus/{id}")
    ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") String id);


    @PostMapping(value = "/algoCode/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> createAlgorithmCode(@PathVariable("id") String id, @RequestPart("code") File code);

    @GetMapping("/algoCode/{id}")
    ResponseEntity<byte[]> readAlgorithmCode(@PathVariable("id") String id);

    @PutMapping(value = "/algoCode/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> updateAlgorithmCode(@PathVariable("id") String id, @RequestPart("code") File code);

    @DeleteMapping("/algoCode/{id}")
    ResponseEntity<Void> deleteAlgorithmCode(@PathVariable("id") String id);


    @PostMapping(value = "/algoJar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> createAlgorithmJar(@PathVariable("id") String id, @RequestPart("jar") File jar);

    @GetMapping("/algoJar/{id}")
    ResponseEntity<byte[]> readAlgorithmJar(@PathVariable("id") String id);

    @PutMapping(value = "/algoJar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> updateAlgorithmJar(@PathVariable("id") String id, @RequestPart("jar") File jar);

    @DeleteMapping("/algoJar/{id}")
    ResponseEntity<Void> deleteAlgorithmJar(@PathVariable("id") String id);


    @PostMapping("/algoMeta/{id}")
    ResponseEntity<Void> createAlgorithmMeta(@PathVariable("id") String id, @RequestBody SrcMeta srcMeta);

    @GetMapping("/algoMeta/{id}")
    ResponseEntity<SrcMeta> readAlgorithmMeta(@PathVariable("id") String id);

    @PutMapping("/algoMeta/{id}")
    ResponseEntity<Void> updateAlgorithmMeta(@PathVariable("id") String id, @RequestBody SrcMeta srcMeta);

    @DeleteMapping("/algoMeta/{id}")
    ResponseEntity<Void> deleteAlgorithmMeta(@PathVariable("id") String id);

}
