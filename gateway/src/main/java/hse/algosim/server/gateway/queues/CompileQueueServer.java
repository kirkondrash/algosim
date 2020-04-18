package hse.algosim.server.gateway.queues;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.compiler.api.CompilerApiClientInstance;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CompileQueueServer{

    private CompilerApiClientInstance compilerApiClient;

    public CompileQueueServer(CompilerApiClientInstance compilerApiClient) {
        this.compilerApiClient = compilerApiClient;
    }

    public void run(ConcurrentLinkedQueue<UUID> compilationQueue, LinkedList<UUID> executionQueue) {
        while (true) {
            UUID id = compilationQueue.peek();
            if (id != null) {
                try {
                    compilerApiClient.compileAlgorithm(id);
                    System.out.println(id+ " sent to worker for compilation");
                    compilationQueue.remove();
                    executionQueue.add(id);
                } catch (ApiException ae) {
                    if (ae.getCode()==503) {
                        System.out.println("Compiler busy!");
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
