package hse.algosim.server.gateway.queues;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;

import java.util.concurrent.ConcurrentLinkedQueue;

class ResultsQueueServer{

    private RepoApiClientInstance repoApiClient;
    private static final ObjectMapper mapper = new ObjectMapper();

    public ResultsQueueServer(RepoApiClientInstance repoApiClient) {
        this.repoApiClient = repoApiClient;
    }

    public void run(ConcurrentLinkedQueue<String> resultQueue) {
        while (true) {
            String id = resultQueue.poll();
            if (id != null) {
                try {
                    SrcStatus srcStatus = repoApiClient.getAlgorithmStatus(id);
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
                    System.out.println(ae.getMessage());
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
