package hse.algosim.server.executor.api;

import hse.algosim.server.FiniteQueueExecutor;
import hse.algosim.server.executor.ExecutorServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;

@Controller
@RequestMapping("${openapi.algosimExecutor.base-path:/api}")
public class ExecuteApiController extends FiniteQueueExecutor implements ExecuteApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ExecuteApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> executeAlgorithm(@PathVariable("id") UUID id) {
        try {
            singleThreadExecutor.submit(()-> ExecutorServer.runExecution(repoApiClient,id));
        }catch (RejectedExecutionException re){
            System.out.println("Caught RejectedExecutionException" + id.toString());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
