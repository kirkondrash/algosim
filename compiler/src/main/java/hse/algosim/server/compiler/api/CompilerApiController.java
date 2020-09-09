package hse.algosim.server.compiler.api;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.FiniteQueueExecutor;
import hse.algosim.server.compiler.CompilerServer;
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
@RequestMapping("${openapi.algosimCompiler.base-path:/api}")
public class CompilerApiController extends FiniteQueueExecutor implements CompilerApi {

    @Autowired
    private Environment env;
    private RepoApiClientInstance repoApiClient;
    private final NativeWebRequest request;

    @Autowired
    public CompilerApiController(NativeWebRequest request, RepoApiClientInstance repoApiClient) {
        this.request = request;
        this.repoApiClient = repoApiClient;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> compileAlgorithm(@PathVariable("id") String id) {
        try {
            singleThreadExecutor.submit(()-> CompilerServer.runCompilation(repoApiClient,id, env.getProperty("framework.project.path")));
            SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
            repoApiClient.updateAlgorithmStatus(id, srcStatus.status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION));
        }catch (RejectedExecutionException re){
            System.out.println(String.format("Compilation queue full for %s on %s", id,hostname));
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
