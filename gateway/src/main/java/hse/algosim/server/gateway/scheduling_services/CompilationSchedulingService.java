package hse.algosim.server.gateway.scheduling_services;

import feign.FeignException;
import hse.algosim.client.compiler.api.CompilerApiClient;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
public class CompilationSchedulingService implements SchedulingService{

    private final Queue<String> scheduledForCompilationIds = new ConcurrentLinkedQueue<>();
    private final List<StatusEnum> applicableStatuses = List.of(StatusEnum.SOURCE_UPLOADED, StatusEnum.SCHEDULED_FOR_COMPILATION, StatusEnum.COMPILING, StatusEnum.COMPILATION_FAILED);
    private final RepoApiClient repoApiClient;
    private final CompilerApiClient compilerApiClient;

    @Autowired
    public CompilationSchedulingService(RepoApiClient repoApiClient, CompilerApiClient compilerApiClient) {
        this.repoApiClient = repoApiClient;
        this.compilerApiClient = compilerApiClient;
    }

    @Override
    public void attemptToExecuteScheduling() {
        Set<String> successfullyScheduledIds = new HashSet<>();
        for (String scheduledId: scheduledForCompilationIds){
            try {
                compilerApiClient.compileAlgorithm(scheduledId);
                successfullyScheduledIds.add(scheduledId);
                log.info(scheduledId + " sent to worker for compilation");
            } catch (FeignException e){
                if (e.status() == 503){
                    log.warn("Compiler busy!");
                } else {
                    log.error(e.responseBody().toString());
                    log.error("Exception while trying to schedule {} compilation", scheduledId,e);
                }
                break;
            }
        }
        scheduledForCompilationIds.removeAll(successfullyScheduledIds);
    }

    @Override
    public boolean cahHandle(StatusEnum status) {
        return applicableStatuses.contains(status);
    }

    @Override
    public void handle(StatusEnum status, String id) {
        switch (status){ // can be refactored later, for now ok
            case SOURCE_UPLOADED:
                scheduledForCompilationIds.add(id);
                break;
            case COMPILATION_FAILED:
                SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id).getBody();
                log.warn("{}: {}", id, srcStatus.toString());
                break;
            default:
                break;
        }
    }

}
