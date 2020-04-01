package hse.algosim.client.api.compiler;

import hse.algosim.client.api.ApiException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

/**
 * API tests for DefaultApi
 */
@Ignore
public class CompilerApiClientInstanceTest {

    private final CompilerApiClientInstance api = new CompilerApiClientInstance();

    
    /**
     * 
     *
     * Gets the sources from the db and compiles them
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void compileAlgorithmTest() throws ApiException {
        UUID id = null;
        api.compileAlgorithm(id);

        // TODO: test validations
    }
    
}
