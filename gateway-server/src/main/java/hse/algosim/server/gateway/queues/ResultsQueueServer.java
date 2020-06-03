package hse.algosim.server.gateway.queues;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;

import java.util.concurrent.ConcurrentLinkedQueue;

class ResultsQueueServer{

    public static void run(RepoApiClientInstance repoApiClient,
                           ConcurrentLinkedQueue<String> resultQueue) {
        while (true) {
            String id = resultQueue.poll();
            if (id != null) {
                try {
                    SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
                    switch (srcStatus.getStatus()){
                        case SUCCESSFULLY_EXECUTED:
                        case EXECUTION_FAILED: {
                            System.out.println(id+": "+srcStatus.toString());
                            break;
                        }
                        case SCHEDULED_FOR_EXECUTION:
                        case EXECUTING: {
                            resultQueue.add(id);
                            break;
                        }
                        default:
                            throw new RuntimeException("This status should not be in queue for execution!");
                    }
                } catch (ApiException ae) {
                    System.out.println(ae.getResponseBody());
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
