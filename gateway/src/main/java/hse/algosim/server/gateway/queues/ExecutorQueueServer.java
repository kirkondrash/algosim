package hse.algosim.server.gateway.queues;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.executor.api.ExecutorApiClientInstance;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;

import java.util.concurrent.ConcurrentLinkedQueue;

class ExecutorQueueServer{

    private RepoApiClientInstance repoApiClient;
    private ExecutorApiClientInstance executorApiClient;

    public ExecutorQueueServer(RepoApiClientInstance repoApiClient, ExecutorApiClientInstance executorApiClient) {
        this.repoApiClient = repoApiClient;
        this.executorApiClient = executorApiClient;
    }

    public void run(ConcurrentLinkedQueue<String> executionQueue, ConcurrentLinkedQueue<String> resultQueue) {
        while (true) {
            String id = executionQueue.poll();
            if (id != null) {
                try {
                    SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
                    switch (srcStatus.getStatus()){
                        case SUCCESSFULLY_COMPILED: {
                            repoApiClient.updateAlgorithmStatus(id, srcStatus.status(SrcStatus.StatusEnum.SCHEDULED_FOR_EXECUTION));
                            executorApiClient.executeAlgorithm(id);
                            System.out.println(id+ " sent to worker for execution");
                            resultQueue.add(id);
                            break;
                        }
                        case SCHEDULED_FOR_COMPILATION:
                        case COMPILING: {
                            executionQueue.add(id);
                            break;
                        }
                        case COMPILATION_FAILED: {
                            System.out.println(id+": "+srcStatus.toString());
                            break;
                        }
                        default:
                            throw new RuntimeException("This status should not be in queue for execution!");
                    }
                } catch (ApiException ae) {
                    if (ae.getCode()==503) {
                        System.out.println("Executor busy!");
                        executionQueue.add(id);
                    } else {
                        System.out.println(ae.getLocalizedMessage());
                    }
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
