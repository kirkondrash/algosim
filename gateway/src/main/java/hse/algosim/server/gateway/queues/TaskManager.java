package hse.algosim.server.gateway.queues;

import hse.algosim.client.compiler.api.CompilerApiClientInstance;
import hse.algosim.client.executor.api.ExecutorApiClientInstance;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.*;

public class TaskManager {
    private RepoApiClientInstance repoApiClient;

    private final static ConcurrentLinkedQueue<UUID> compilationQueue = new ConcurrentLinkedQueue<>(); // written by multiple threads
    private final static LinkedList<UUID> executionQueue = new LinkedList<>(); // only one thread accesses it on write
    private final static LinkedList<UUID> resultQueue = new LinkedList<>(); // only one thread accesses it on write
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

    public void scheduleCodeAnalysis(UUID id, MultipartFile code) throws Exception{
        File f = new File(id.toString()).getAbsoluteFile();
        code.transferTo(f);
        repoApiClient.uploadAlgorithmCode(id,f);
        repoApiClient.uploadAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SCHEDULED_FOR_COMPILATION));
        f.delete();
        compilationQueue.add(id);
    }

}
