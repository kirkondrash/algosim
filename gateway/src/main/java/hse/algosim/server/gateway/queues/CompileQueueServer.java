package hse.algosim.server.gateway.queues;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.compiler.api.CompilerApiClientInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CompileQueueServer{

    public static void run(CompilerApiClientInstance compilerApiClient,
                           ConcurrentLinkedQueue<String> compilationQueue,
                           ConcurrentLinkedQueue<String> executionQueue) {
        while (true) {
            String id = compilationQueue.peek();
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
                        System.out.println(ae.getResponseBody());
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
