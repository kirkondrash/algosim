package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.executor.api.ExecutorApiClientInstance;
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
class ExecutionSchedulingService implements SchedulingService{

    private Queue<String> scheduledForExecutionIds = new ConcurrentLinkedQueue<>();
    private List<StatusEnum> applicableStatuses = List.of(StatusEnum.SUCCESSFULLY_COMPILED, StatusEnum.SCHEDULED_FOR_EXECUTION, StatusEnum.EXECUTING, StatusEnum.EXECUTION_FAILED);
    private RepoApiClientInstance repoApiClient;
    private ExecutorApiClientInstance executorApiClient;

    @Autowired
    public ExecutionSchedulingService(RepoApiClientInstance repoApiClient, ExecutorApiClientInstance executorApiClient) {
        this.repoApiClient = repoApiClient;
        this.executorApiClient = executorApiClient;
    }

    @Scheduled(fixedDelay = 2000)
    public void algorithmCompilationAttempting() {
        attemptToExecuteScheduling(scheduledForExecutionIds, (id) -> {
            try {
                executorApiClient.executeAlgorithm(id);
                System.out.println(id + " sent to worker for execution");
                return true;
            } catch (ApiException ae) {
                if (ae.getCode() == 503) {
                    log.info("Executor busy!");
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
            case SUCCESSFULLY_COMPILED:
                scheduledForExecutionIds.add(id);
                break;
            case EXECUTION_FAILED:
                SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
                System.out.println(String.format("%s: %s", id, srcStatus.toString()));
                break;
            default:
                break;
        }
    }
}
