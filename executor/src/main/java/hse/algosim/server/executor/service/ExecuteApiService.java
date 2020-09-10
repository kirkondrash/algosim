package hse.algosim.server.executor.service;

import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecuteApiService {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final ExecutionRunner executionRunner;
    private final RepoApiClient repoApiClient;

    @Autowired
    public ExecuteApiService(ThreadPoolTaskExecutor taskExecutor, ExecutionRunner executionRunner, RepoApiClient repoApiClient) {
        this.taskExecutor = taskExecutor;
        this.executionRunner = executionRunner;
        this.repoApiClient = repoApiClient;
    }


    public void executeAlgorithm( String id) {
        executionRunner.runExecution(id);
        if (taskExecutor.getThreadPoolExecutor().getQueue().size() > 0) {
            SrcStatus.SrcStatusBuilder srcStatus = repoApiClient.readAlgorithmStatus(id).getBody().toBuilder();
            srcStatus.status(SrcStatus.StatusEnum.SCHEDULED_FOR_EXECUTION);
            repoApiClient.updateAlgorithmStatus(id, srcStatus.build());
        }
    }

    public boolean getReadiness(){
        return taskExecutor.getThreadPoolExecutor().getQueue().size()<2;
    }
}
