package hse.algosim.client.compiler.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="compiler-client", url = "${compiler.basePath:undefined}")
public interface CompilerApiClient {

    @PostMapping("/compile/{id}")
    ResponseEntity<Void> compileAlgorithm(@PathVariable("id") String id);
}
