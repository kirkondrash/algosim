package hse.algosim.client.compiler.api;

import hse.algosim.client.api.ApiException;
import org.junit.Ignore;
import org.junit.Test;

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
        String id = null;
        api.compileAlgorithm(id);

        // TODO: test validations
    }
    
}
