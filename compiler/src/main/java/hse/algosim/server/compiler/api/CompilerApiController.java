package hse.algosim.server.compiler.api;

import hse.algosim.server.FiniteQueueExecutor;
import hse.algosim.server.compiler.CompilerServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;

@Controller
@RequestMapping("${openapi.algosimCompiler.base-path:/api}")
public class CompilerApiController extends FiniteQueueExecutor implements CompilerApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CompilerApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> compileAlgorithm(@PathVariable("id") UUID id) {
        try {
            singleThreadExecutor.submit(()-> CompilerServer.runCompilation(repoApiClient,id));
        }catch (RejectedExecutionException re){
            System.out.println(String.format("Compilation queue full for %s on %s",id.toString(),hostname));
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
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
