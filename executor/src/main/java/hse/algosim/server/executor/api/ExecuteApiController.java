package hse.algosim.server.executor.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.FiniteQueueExecutor;
import hse.algosim.server.executor.ExecutorService;
import hse.algosim.server.model.SrcStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

@Controller
@RequestMapping("${openapi.algosimExecutor.base-path:/api}")
@Slf4j
public class ExecuteApiController extends FiniteQueueExecutor implements ExecuteApi {

    @Autowired
    private Environment env;
    private RepoApiClient repoApiClient;
    private ExecutorService executorService;
    private final NativeWebRequest request;

    @Autowired
    public ExecuteApiController(NativeWebRequest request, RepoApiClient repoApiClient, ExecutorService executorService) {
        this.request = request;
        this.repoApiClient = repoApiClient;
        this.executorService = executorService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> executeAlgorithm(@PathVariable("id") String id) {
        try {
            singleThreadExecutor.submit(()-> executorService.runExecution(
                    repoApiClient,
                    id,
                    env.getProperty("framework.quotes.path"),
                    env.getProperty("spring.datasource.username"),
                    env.getProperty("spring.datasource.password"),
                    env.getProperty("spring.datasource.url")
                    ));
            SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id).getBody();
            srcStatus.setStatus(SrcStatus.StatusEnum.SCHEDULED_FOR_EXECUTION);
            repoApiClient.updateAlgorithmStatus(id, srcStatus);
        }catch (RejectedExecutionException re){
            System.out.println(String.format("Execution queue full for %s on %s", id,hostname));
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (FeignException e) {
            log.error("{}",e.getCause());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> getReadiness() {
        if (singleThreadExecutor.getQueue().size()<2)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
