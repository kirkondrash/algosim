package hse.algosim.client.api.executor;

import hse.algosim.client.api.ApiException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

/**
 * API tests for DefaultApi
 */
@Ignore
public class ExecutorApiClientInstanceTest {

    private final ExecutorApiClientInstance api = new ExecutorApiClientInstance();

    
    /**
     * 
     *
     * Gets the artifact from nexus, executes and benchmarks it
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeAlgorithmTest() throws ApiException {
        UUID id = null;
        api.executeAlgorithm(id);

        // TODO: test validations
    }
    
}
