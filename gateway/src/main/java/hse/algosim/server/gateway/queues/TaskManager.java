package hse.algosim.server.gateway.queues;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.compiler.api.CompilerApiClientInstance;
import hse.algosim.client.executor.api.ExecutorApiClientInstance;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class TaskManager {
    private RepoApiClientInstance repoApiClient;

    private final static ConcurrentLinkedQueue<String> compilationQueue = new ConcurrentLinkedQueue<>(); // written by multiple threads
    private final static ConcurrentLinkedQueue<String> executionQueue = new ConcurrentLinkedQueue<>(); // only one thread accesses it on write
    private final static ConcurrentLinkedQueue<String> resultQueue = new ConcurrentLinkedQueue<>(); // only one thread accesses it on write
    private final static ExecutorService taskQueueThread =  new ThreadPoolExecutor(
            3,
            3,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public TaskManager(RepoApiClientInstance repoApiClient,
                       CompilerApiClientInstance compilerApiClient,
                       ExecutorApiClientInstance executorApiClient){
        this.repoApiClient = repoApiClient;

        CompileQueueServer compileQueueServer = new CompileQueueServer(compilerApiClient);
        ExecutorQueueServer executorQueueServer = new ExecutorQueueServer(repoApiClient,executorApiClient);
        ResultsQueueServer resultsQueueServer = new ResultsQueueServer(repoApiClient);

        taskQueueThread.submit(() -> compileQueueServer.run(compilationQueue,executionQueue));
        taskQueueThread.submit(() -> executorQueueServer.run(executionQueue,resultQueue));
        taskQueueThread.submit(() -> resultsQueueServer.run(resultQueue));
    }

    public void scheduleCodeAnalysis(String id, MultipartFile code) throws ApiException {
        File f = new File(id).getAbsoluteFile();
        try {
            code.transferTo(f);
            repoApiClient.uploadAlgorithmCode(id,f);
            repoApiClient.uploadAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION));
        } catch (ApiException ae){
            if (ae.getCode() == 409){
                repoApiClient.replaceAlgorithmCode(id,f);
                repoApiClient.replaceAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION));
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
