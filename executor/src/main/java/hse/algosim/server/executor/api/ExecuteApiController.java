package hse.algosim.server.executor.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.executor.service.ExecuteApiService;
import hse.algosim.server.executor.service.ExecutionRunner;
import hse.algosim.server.model.SrcStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

@Controller
@RequestMapping("${openapi.algosimExecutor.base-path:/api}")
@Slf4j
public class ExecuteApiController implements ExecuteApi {

    private final ExecuteApiService executeApiService;
    private final static String hostname = Optional.ofNullable(System.getenv("HOSTNAME")).orElse("undefined");

    @Autowired
    public ExecuteApiController(ExecuteApiService executeApiService) {
        this.executeApiService = executeApiService;
    }

    @Override
    public ResponseEntity<Void> executeAlgorithm(@PathVariable("id") String id) {
        try {
            executeApiService.executeAlgorithm(id);
        }catch (RejectedExecutionException re){
            log.warn("Execution queue full for {} on {}", id,hostname);
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (FeignException e) {
            log.error("Exception while setting {} status before execution",id, e);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> getReadiness() {
        if (executeApiService.getReadiness())
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
