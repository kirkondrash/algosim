package hse.algosim.server.executor.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.executor.service.ExecutorService;
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

    private final static String hostname = Optional.ofNullable(System.getenv("HOSTNAME")).orElse("undefined");
    private final ThreadPoolTaskExecutor taskExecutor;
    private final RepoApiClient repoApiClient;
    private final ExecutorService executorService;

    @Autowired
    public ExecuteApiController(ThreadPoolTaskExecutor taskExecutor, RepoApiClient repoApiClient, ExecutorService executorService) {
        this.taskExecutor = taskExecutor;
        this.repoApiClient = repoApiClient;
        this.executorService = executorService;
    }

    @Override
    public ResponseEntity<Void> executeAlgorithm(@PathVariable("id") String id) {
        try {
            executorService.runExecution(id);
            SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id).getBody();
            srcStatus.setStatus(SrcStatus.StatusEnum.SCHEDULED_FOR_EXECUTION);
            repoApiClient.updateAlgorithmStatus(id, srcStatus);
        }catch (RejectedExecutionException re){
            log.warn("Execution queue full for {} on {}", id,hostname);
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (FeignException e) {
            log.error("{}",e.getCause());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> getReadiness() {
        if (taskExecutor.getThreadPoolExecutor().getQueue().size()<2)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
