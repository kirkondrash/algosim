package hse.algosim.server.gateway.scheduling_services;

import feign.FeignException;
import hse.algosim.client.executor.api.ExecutorApiClient;
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
class ExecutionSchedulingService implements SchedulingService{

    private final Queue<String> scheduledForExecutionIds = new ConcurrentLinkedQueue<>();
    private final List<StatusEnum> applicableStatuses = List.of(StatusEnum.SUCCESSFULLY_COMPILED, StatusEnum.SCHEDULED_FOR_EXECUTION, StatusEnum.EXECUTING, StatusEnum.EXECUTION_FAILED);
    private final RepoApiClient repoApiClient;
    private final ExecutorApiClient executorApiClient;

    @Autowired
    public ExecutionSchedulingService(RepoApiClient repoApiClient, ExecutorApiClient executorApiClient) {
        this.repoApiClient = repoApiClient;
        this.executorApiClient = executorApiClient;
    }

    @Override
    public void attemptToExecuteScheduling() {
        Set<String> successfullyScheduledIds = new HashSet<>();
        for (String scheduledId: scheduledForExecutionIds){
            try {
                executorApiClient.executeAlgorithm(scheduledId);
                successfullyScheduledIds.add(scheduledId);
                log.info(scheduledId + " sent to worker for execution");
            } catch (FeignException e){
                if (e.status() == 503){
                    log.warn("Executor busy!");
                } else {
                    log.error(e.responseBody().toString());
                    log.error("Exception while trying to schedule {} execution", scheduledId,e);
                }
                break;
            }
        }
        scheduledForExecutionIds.removeAll(successfullyScheduledIds);
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
