package hse.algosim.client.repo.api;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.model.SrcMeta;
import hse.algosim.client.model.SrcStatus;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * API tests for DefaultApi
 */
@Ignore
public class RepoApiClientInstanceTest {

    private final RepoApiClientInstance api = new RepoApiClientInstance();

    
    /**
     * 
     *
     * Replaces the algorithm status and/or benchmarks
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void replaceAlgorithmStatusTest() throws ApiException {
        String id = null;
        SrcStatus srcStatus = null;
        api.replaceAlgorithmStatus(id, srcStatus);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Deletes algorithm sources based on the id supplied
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAlgorithmCodeTest() throws ApiException {
        String id = null;
        api.deleteAlgorithmCode(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * deletes the algorithm jar based on the id supplied
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAlgorithmJarTest() throws ApiException {
        String id = null;
        api.deleteAlgorithmJar(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Clears algorithm metadata
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAlgorithmMetaTest() throws ApiException {
        String id = null;
        api.deleteAlgorithmMeta(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns an algorithm status
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAlgorithmStatusTest() throws ApiException {
        String id = null;
        api.deleteAlgorithmStatus(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns algorithm sources based on id supplied
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAlgorithmCodeTest() throws ApiException {
        String id = null;
        File response = api.getAlgorithmCode(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns algorithm jar
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAlgorithmJarTest() throws ApiException {
        String id = null;
        File response = api.getAlgorithmJar(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns algorithm metadata
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAlgorithmMetaTest() throws ApiException {
        String id = null;
        SrcMeta response = api.getAlgorithmMeta(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns an algorithm status
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAlgorithmStatusTest() throws ApiException {
        String id = null;
        SrcStatus response = api.getAlgorithmStatus(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Uploads the algorithm sources based on id supplied
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void uploadAlgorithmCodeTest() throws ApiException {
        String id = null;
        File code = null;
        api.uploadAlgorithmCode(id, code);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Uploads algorithm artifact
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void uploadAlgorithmJarTest() throws ApiException {
        String id = null;
        File jar = null;
        api.uploadAlgorithmJar(id, jar);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Loads algorithm metadata like description or author
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void uploadAlgorithmMetaTest() throws ApiException {
        String id = null;
        SrcMeta srcMeta = null;
        api.uploadAlgorithmMeta(id, srcMeta);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Uploads the algorithm status and/or benchmarks
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void uploadAlgorithmStatusTest() throws ApiException {
        String id = null;
        SrcStatus srcStatus = null;
        api.uploadAlgorithmStatus(id, srcStatus);

        // TODO: test validations
    }
    
}
