package hse.algosim.server.gateway.scheduling_services;

import feign.FeignException;
import hse.algosim.client.executor.api.ExecutorApiClient;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
class ExecutionSchedulingService implements SchedulingService{

    private Queue<String> scheduledForExecutionIds = new ConcurrentLinkedQueue<>();
    private List<StatusEnum> applicableStatuses = List.of(StatusEnum.SUCCESSFULLY_COMPILED, StatusEnum.SCHEDULED_FOR_EXECUTION, StatusEnum.EXECUTING, StatusEnum.EXECUTION_FAILED);
    private RepoApiClient repoApiClient;
    private ExecutorApiClient executorApiClient;

    @Autowired
    public ExecutionSchedulingService(RepoApiClient repoApiClient, ExecutorApiClient executorApiClient) {
        this.repoApiClient = repoApiClient;
        this.executorApiClient = executorApiClient;
    }

    @Scheduled(fixedDelay = 2000)
    public void algorithmCompilationAttempting() {
        attemptToExecuteScheduling(scheduledForExecutionIds, (id) -> {
            try {
                executorApiClient.executeAlgorithm(id);
                log.info(id + " sent to worker for execution");
                return true;
            } catch (FeignException e){
                if (e.status() == 503){
                    log.warn("Executor busy!");
                } else {
                    log.error(e.responseBody().toString());
                    log.error("{}",e.getCause());
                }
                return false;
            }
        });
    }

    @Override
    public boolean cahHandle(StatusEnum status) {
        return applicableStatuses.contains(status);
    }

    @Override
    public void handle(StatusEnum status, String id) {
        switch (status){ // can be refactored later, for now ok
            case SUCCESSFULLY_COMPILED:
                scheduledForExecutionIds.add(id);
                break;
            case EXECUTION_FAILED:
                SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id).getBody();
                log.warn("{}: {}", id, srcStatus.toString());
                break;
            default:
                break;
        }
    }
}
