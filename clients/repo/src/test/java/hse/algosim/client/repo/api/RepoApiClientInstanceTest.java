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

    private final RepoApiClientInstance api = new RepoApiClientInstance("user","password","http://repo:8080/api");

    
    /**
     * 
     *
     * Replaces the algorithm status and/or benchmarks
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateAlgorithmStatusTest() throws ApiException {
        String id = null;
        SrcStatus srcStatus = null;
        api.updateAlgorithmStatus(id, srcStatus);

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
    public void readAlgorithmCodeTest() throws ApiException {
        String id = null;
        File response = api.readAlgorithmCode(id);

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
    public void readAlgorithmJarTest() throws ApiException {
        String id = null;
        File response = api.readAlgorithmJar(id);

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
    public void readAlgorithmMetaTest() throws ApiException {
        String id = null;
        SrcMeta response = api.readAlgorithmMeta(id);

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
    public void readAlgorithmStatusTest() throws ApiException {
        String id = null;
        SrcStatus response = api.readAlgorithmStatus(id);

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
    public void createAlgorithmCodeTest() throws ApiException {
        String id = null;
        File code = null;
        api.createAlgorithmCode(id, code);

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
    public void createAlgorithmJarTest() throws ApiException {
        String id = null;
        File jar = null;
        api.createAlgorithmJar(id, jar);

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
    public void createAlgorithmMetaTest() throws ApiException {
        String id = null;
        SrcMeta srcMeta = null;
        api.createAlgorithmMeta(id, srcMeta);

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
    public void createAlgorithmStatusTest() throws ApiException {
        String id = null;
        SrcStatus srcStatus = null;
        api.createAlgorithmStatus(id, srcStatus);

        // TODO: test validations
    }
    
}
