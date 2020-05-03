package hse.algosim.server.gateway.queues;

import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.compiler.api.CompilerApiClientInstance;
import hse.algosim.client.executor.api.ExecutorApiClientInstance;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

@Component
public class TaskManager {
    private RepoApiClientInstance repoApiClient;

    private final static ConcurrentLinkedQueue<String> compilationQueue = new ConcurrentLinkedQueue<>();
    private final static ConcurrentLinkedQueue<String> executionQueue = new ConcurrentLinkedQueue<>();
    private final static ConcurrentLinkedQueue<String> resultQueue = new ConcurrentLinkedQueue<>();
    private final static ExecutorService taskQueueThread =  new ThreadPoolExecutor(
            3,
            3,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    @Autowired
    public TaskManager(RepoApiClientInstance repoApiClient,
                       CompilerApiClientInstance compilerApiClient,
                       ExecutorApiClientInstance executorApiClient){
        this.repoApiClient = repoApiClient;
        taskQueueThread.submit(() -> CompileQueueServer.run(compilerApiClient,compilationQueue,executionQueue));
        taskQueueThread.submit(() -> ExecutorQueueServer.run(repoApiClient, executorApiClient, executionQueue,resultQueue));
        taskQueueThread.submit(() -> ResultsQueueServer.run(repoApiClient, resultQueue));
    }

    public void scheduleCodeAnalysis(String id, MultipartFile code) throws ApiException {
        File f = new File(id).getAbsoluteFile();
        try {
            code.transferTo(f);
            repoApiClient.createAlgorithmCode(id,f);
            repoApiClient.createAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION));
        } catch (ApiException ae){
            if (ae.getCode() == 409){
                repoApiClient.updateAlgorithmCode(id,f);
                repoApiClient.updateAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION));
            } else {
                throw ae;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        f.delete();
        compilationQueue.add(id);
    }

}
