package hse.algosim.server.executor.api;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.FiniteQueueExecutor;
import hse.algosim.server.executor.ExecutorServer;
import hse.algosim.server.model.SrcStatus;
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
public class ExecuteApiController extends FiniteQueueExecutor implements ExecuteApi {

    @Autowired
    private Environment env;
    private RepoApiClientInstance repoApiClient;
    private final NativeWebRequest request;

    @Autowired
    public ExecuteApiController(NativeWebRequest request, RepoApiClientInstance repoApiClient) {
        this.request = request;
        this.repoApiClient = repoApiClient;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> executeAlgorithm(@PathVariable("id") String id) {
        try {
            singleThreadExecutor.submit(()-> ExecutorServer.runExecution(
                    repoApiClient,
                    id,
                    env.getProperty("framework.quotes.path"),
                    env.getProperty("spring.datasource.username"),
                    env.getProperty("spring.datasource.password"),
                    env.getProperty("spring.datasource.url")
                    ));
            SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
            srcStatus.setStatus(SrcStatus.StatusEnum.SCHEDULED_FOR_EXECUTION);
            repoApiClient.updateAlgorithmStatus(id, srcStatus);
        }catch (RejectedExecutionException re){
            System.out.println(String.format("Execution queue full for %s on %s", id,hostname));
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ApiException e) {
            e.printStackTrace();
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
