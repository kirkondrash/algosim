package hse.algosim.server.compiler.service;

import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class CompilerApiService {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final CompilerRunner compilerRunner;
    private final RepoApiClient repoApiClient;

    @Autowired
    public CompilerApiService(ThreadPoolTaskExecutor taskExecutor, CompilerRunner compilerRunner, RepoApiClient repoApiClient) {
        this.taskExecutor = taskExecutor;
        this.compilerRunner = compilerRunner;
        this.repoApiClient = repoApiClient;
    }


    public void compileAlgorithm( String id) {
        if (taskExecutor.getThreadPoolExecutor().getActiveCount() > 0 && getReadiness()) {
            SrcStatus.SrcStatusBuilder srcStatus = repoApiClient.readAlgorithmStatus(id).getBody().toBuilder();
            srcStatus.status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION);
            repoApiClient.updateAlgorithmStatus(id, srcStatus.build());
        }
        compilerRunner.runCompilation(id);
    }

    public boolean getReadiness(){
        return taskExecutor.getThreadPoolExecutor().getQueue().size()<2;
    }
}
