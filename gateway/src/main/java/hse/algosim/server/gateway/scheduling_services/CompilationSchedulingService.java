package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.compiler.api.CompilerApiClientInstance;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
public class CompilationSchedulingService implements SchedulingService{

    private Queue<String> scheduledForCompilationIds = new ConcurrentLinkedQueue<>();
    private List<StatusEnum> applicableStatuses = List.of(StatusEnum.SOURCE_UPLOADED, StatusEnum.SCHEDULED_FOR_COMPILATION, StatusEnum.COMPILING, StatusEnum.COMPILATION_FAILED);
    private RepoApiClientInstance repoApiClient;
    private CompilerApiClientInstance compilerApiClient;

    @Autowired
    public CompilationSchedulingService(RepoApiClientInstance repoApiClient,CompilerApiClientInstance compilerApiClient) {
        this.repoApiClient = repoApiClient;
        this.compilerApiClient = compilerApiClient;
    }

    @Scheduled(fixedDelay = 2000)
    public void algorithmCompilationAttempting() {
        attemptToExecuteScheduling(scheduledForCompilationIds, (id) -> {
            try {
                compilerApiClient.compileAlgorithm(id);
                log.info(id + " sent to worker for compilation");
                return true;
            } catch (ApiException ae) {
                if (ae.getCode() == 503) {
                    log.info("Compiler busy!");
                } else {
                    System.out.println(ae.getResponseBody());
                }
                return false;
            }
        });
    }

    @Override
    public boolean cahHandle(StatusEnum status) {
        return applicableStatuses.contains(status);
    }

    @SneakyThrows
    @Override
    public void handle(StatusEnum status, String id) {
        switch (status){ // can be refactored later, for now ok
            case SOURCE_UPLOADED:
                scheduledForCompilationIds.add(id);
                break;
            case COMPILATION_FAILED:
                SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
                log.warn("{}: {}", id, srcStatus.toString());
                break;
            default:
                break;
        }
    }

}
