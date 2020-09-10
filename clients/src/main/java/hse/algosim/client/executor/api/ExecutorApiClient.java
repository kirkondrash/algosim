package hse.algosim.client.executor.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="executor-client", url = "${executor.basePath:undefined}")
public interface ExecutorApiClient {

    @PostMapping("/execute/{id}")
    ResponseEntity<Void> executeAlgorithm(@PathVariable("id") String id);
}
