package hse.algosim.server.compiler.api;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.compiler.service.CompilerService;
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
@RequestMapping("${openapi.algosimCompiler.base-path:/api}")
@Slf4j
public class CompilerApiController implements CompilerApi {

    private final static String hostname = Optional.ofNullable(System.getenv("HOSTNAME")).orElse("undefined");
    private final ThreadPoolTaskExecutor taskExecutor;
    private final RepoApiClient repoApiClient;
    private final CompilerService compilerService;

    @Autowired
    public CompilerApiController(ThreadPoolTaskExecutor taskExecutor, RepoApiClient repoApiClient, CompilerService compilerService){
        this.taskExecutor = taskExecutor;
        this.repoApiClient = repoApiClient;
        this.compilerService = compilerService;
    }

    @Override
    public ResponseEntity<Void> compileAlgorithm(@PathVariable("id") String id) {
        try {
            compilerService.runCompilation(id);
            SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id).getBody();
            srcStatus.setStatus(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION);
            repoApiClient.updateAlgorithmStatus(id, srcStatus);
        }catch (RejectedExecutionException re){
            log.warn("Compilation queue full for {} on {}", id,hostname);
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (FeignException e) {
            log.error(e.responseBody().toString());
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
