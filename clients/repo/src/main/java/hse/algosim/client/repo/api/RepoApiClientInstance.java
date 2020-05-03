package hse.algosim.client.repo.api;

import hse.algosim.client.api.*;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcMeta;
import hse.algosim.server.model.SrcStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RepoApiClientInstance {
    private ApiClient localVarApiClient;

    @Autowired
    public RepoApiClientInstance(@Value("${user.username}") String username, @Value("${user.password}") String password, @Value("${repo.basePath:http://repo:8080/api}") String basePath) {
        this.localVarApiClient = new ApiClient(username, password, basePath);
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for deleteAlgorithmCode
     * @param id id of algorithm to delete (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Source code successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmCodeCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoCode/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteAlgorithmCodeValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = deleteAlgorithmCodeCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Deletes algorithm source
     * @param id id of algorithm to delete (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Source code successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void deleteAlgorithmCode(String id) throws ApiException {
        deleteAlgorithmCodeWithHttpInfo(id);
    }

    /**
     * 
     * Deletes algorithm source
     * @param id id of algorithm to delete (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Source code successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteAlgorithmCodeWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = deleteAlgorithmCodeValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Deletes algorithm source
     * @param id id of algorithm to delete (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Source code successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmCodeAsync(String id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteAlgorithmCodeValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteAlgorithmJar
     * @param id id of algorithm to delete (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Artifact successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmJarCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoJar/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteAlgorithmJarValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = deleteAlgorithmJarCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * deletes the algorithm jar based on the id supplied
     * @param id id of algorithm to delete (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Artifact successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void deleteAlgorithmJar(String id) throws ApiException {
        deleteAlgorithmJarWithHttpInfo(id);
    }

    /**
     * 
     * deletes the algorithm jar based on the id supplied
     * @param id id of algorithm to delete (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Artifact successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteAlgorithmJarWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = deleteAlgorithmJarValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * deletes the algorithm jar based on the id supplied
     * @param id id of algorithm to delete (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Artifact successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmJarAsync(String id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteAlgorithmJarValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteAlgorithmMeta
     * @param id id of algorithm which metadata will be deleted (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Metadata successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmMetaCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoMeta/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteAlgorithmMetaValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = deleteAlgorithmMetaCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Deletes algorithm metadata based on the id supplied
     * @param id id of algorithm which metadata will be deleted (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Metadata successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void deleteAlgorithmMeta(String id) throws ApiException {
        deleteAlgorithmMetaWithHttpInfo(id);
    }

    /**
     * 
     * Deletes algorithm metadata based on the id supplied
     * @param id id of algorithm which metadata will be deleted (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Metadata successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteAlgorithmMetaWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = deleteAlgorithmMetaValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Deletes algorithm metadata based on the id supplied
     * @param id id of algorithm which metadata will be deleted (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Metadata successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmMetaAsync(String id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteAlgorithmMetaValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteAlgorithmStatus
     * @param id id of algorithm which status will be deleted (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmStatusCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoStatus/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteAlgorithmStatusValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = deleteAlgorithmStatusCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Returns an algorithm status
     * @param id id of algorithm which status will be deleted (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void deleteAlgorithmStatus(String id) throws ApiException {
        deleteAlgorithmStatusWithHttpInfo(id);
    }

    /**
     * 
     * Returns an algorithm status
     * @param id id of algorithm which status will be deleted (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteAlgorithmStatusWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = deleteAlgorithmStatusValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Returns an algorithm status
     * @param id id of algorithm which status will be deleted (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteAlgorithmStatusAsync(String id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteAlgorithmStatusValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for readAlgorithmCode
     * @param id id of algorithm to fetch (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this UUI </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmCodeCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoCode/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/octet-stream"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call readAlgorithmCodeValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling readAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = readAlgorithmCodeCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Returns algorithm source
     * @param id id of algorithm to fetch (required)
     * @return File
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this UUI </td><td>  -  </td></tr>
     </table>
     */
    public File readAlgorithmCode(String id) throws ApiException {
        ApiResponse<File> localVarResp = readAlgorithmCodeWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * 
     * Returns algorithm source
     * @param id id of algorithm to fetch (required)
     * @return ApiResponse&lt;File&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this UUI </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<File> readAlgorithmCodeWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = readAlgorithmCodeValidateBeforeCall(id, null);
        //Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, File.class);
    }

    /**
     *  (asynchronously)
     * Returns algorithm source
     * @param id id of algorithm to fetch (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this UUI </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmCodeAsync(String id, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = readAlgorithmCodeValidateBeforeCall(id, _callback);
        //Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, File.class, _callback);
        return localVarCall;
    }
    /**
     * Build call for readAlgorithmJar
     * @param id id of algorithm which jar will be fetched (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmJarCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoJar/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/octet-stream"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  "basicAuth"};
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call readAlgorithmJarValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling readAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = readAlgorithmJarCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Returns algorithm jar
     * @param id id of algorithm which jar will be fetched (required)
     * @return File
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public File readAlgorithmJar(String id) throws ApiException {
        ApiResponse<File> localVarResp = readAlgorithmJarWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * 
     * Returns algorithm jar
     * @param id id of algorithm which jar will be fetched (required)
     * @return ApiResponse&lt;File&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<File> readAlgorithmJarWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = readAlgorithmJarValidateBeforeCall(id, null);
        //Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, File.class);
    }

    /**
     *  (asynchronously)
     * Returns algorithm jar
     * @param id id of algorithm which jar will be fetched (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmJarAsync(String id, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = readAlgorithmJarValidateBeforeCall(id, _callback);
//        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, File.class, _callback);
        return localVarCall;
    }
    /**
     * Build call for readAlgorithmMeta
     * @param id id of algorithm which metadata is fetched (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmMetaCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoMeta/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call readAlgorithmMetaValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling readAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = readAlgorithmMetaCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Returns algorithm metadata
     * @param id id of algorithm which metadata is fetched (required)
     * @return SrcMeta
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public SrcMeta readAlgorithmMeta(String id) throws ApiException {
        ApiResponse<SrcMeta> localVarResp = readAlgorithmMetaWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * 
     * Returns algorithm metadata
     * @param id id of algorithm which metadata is fetched (required)
     * @return ApiResponse&lt;SrcMeta&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SrcMeta> readAlgorithmMetaWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = readAlgorithmMetaValidateBeforeCall(id, null);
//        Type localVarReturnType = new TypeToken<SrcMeta>(){}.getType();
        return localVarApiClient.execute(localVarCall, SrcMeta.class);
    }

    /**
     *  (asynchronously)
     * Returns algorithm metadata
     * @param id id of algorithm which metadata is fetched (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmMetaAsync(String id, final ApiCallback<SrcMeta> _callback) throws ApiException {

        okhttp3.Call localVarCall = readAlgorithmMetaValidateBeforeCall(id, _callback);
//        Type localVarReturnType = new TypeToken<SrcMeta>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, SrcMeta.class, _callback);
        return localVarCall;
    }
    /**
     * Build call for readAlgorithmStatus
     * @param id id of algorithm which status will be fetched (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmStatusCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoStatus/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call readAlgorithmStatusValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling readAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = readAlgorithmStatusCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Returns an algorithm status
     * @param id id of algorithm which status will be fetched (required)
     * @return SrcStatus
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public SrcStatus readAlgorithmStatus(String id) throws ApiException {
        ApiResponse<SrcStatus> localVarResp = readAlgorithmStatusWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * 
     * Returns an algorithm status
     * @param id id of algorithm which status will be fetched (required)
     * @return ApiResponse&lt;SrcStatus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SrcStatus> readAlgorithmStatusWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = readAlgorithmStatusValidateBeforeCall(id, null);
//        Type localVarReturnType = new TypeToken<SrcStatus>(){}.getType();
        return localVarApiClient.execute(localVarCall, SrcStatus.class);
    }

    /**
     *  (asynchronously)
     * Returns an algorithm status
     * @param id id of algorithm which status will be fetched (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully fetched </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call readAlgorithmStatusAsync(String id, final ApiCallback<SrcStatus> _callback) throws ApiException {

        okhttp3.Call localVarCall = readAlgorithmStatusValidateBeforeCall(id, _callback);
//        Type localVarReturnType = new TypeToken<SrcStatus>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, SrcStatus.class, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTopCode
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getTopCodeCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/getTopCode";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getTopCodeValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getTopCodeCall(_callback);
        return localVarCall;

    }

    /**
     * 
     * Returns some 10 algos ids
     * @return IdArray
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code </td><td>  -  </td></tr>
     </table>
     */
    public IdArray getTopCode() throws ApiException {
        ApiResponse<IdArray> localVarResp = getTopCodeWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * 
     * Returns some 10 algos ids
     * @return ApiResponse&lt;IdArray&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<IdArray> getTopCodeWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = getTopCodeValidateBeforeCall(null);
//        Type localVarReturnType = new TypeToken<IdArray>(){}.getType();
        return localVarApiClient.execute(localVarCall, IdArray.class);
    }

    /**
     *  (asynchronously)
     * Returns some 10 algos ids
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getTopCodeAsync(final ApiCallback<IdArray> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTopCodeValidateBeforeCall(_callback);
//        Type localVarReturnType = new TypeToken<IdArray>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, IdArray.class, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateAlgorithmCode
     * @param id id of algorithm to replace (required)
     * @param code  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully replaced </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmCodeCall(String id, File code, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoCode/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (code != null) {
            localVarFormParams.put("code", code);
        }

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateAlgorithmCodeValidateBeforeCall(String id, File code, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = updateAlgorithmCodeCall(id, code, _callback);
        return localVarCall;

    }

    /**
     * 
     * Replaces the algorithm source
     * @param id id of algorithm to replace (required)
     * @param code  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully replaced </td><td>  -  </td></tr>
     </table>
     */
    public void updateAlgorithmCode(String id, File code) throws ApiException {
        updateAlgorithmCodeWithHttpInfo(id, code);
    }

    /**
     * 
     * Replaces the algorithm source
     * @param id id of algorithm to replace (required)
     * @param code  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully replaced </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> updateAlgorithmCodeWithHttpInfo(String id, File code) throws ApiException {
        okhttp3.Call localVarCall = updateAlgorithmCodeValidateBeforeCall(id, code, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Replaces the algorithm source
     * @param id id of algorithm to replace (required)
     * @param code  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Source code successfully replaced </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmCodeAsync(String id, File code, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateAlgorithmCodeValidateBeforeCall(id, code, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateAlgorithmJar
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmJarCall(String id, File jar, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoJar/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (jar != null) {
            localVarFormParams.put("jar", jar);
        }

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateAlgorithmJarValidateBeforeCall(String id, File jar, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = updateAlgorithmJarCall(id, jar, _callback);
        return localVarCall;

    }

    /**
     * 
     * Replaces algorithm artifact
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void updateAlgorithmJar(String id, File jar) throws ApiException {
        updateAlgorithmJarWithHttpInfo(id, jar);
    }

    /**
     * 
     * Replaces algorithm artifact
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> updateAlgorithmJarWithHttpInfo(String id, File jar) throws ApiException {
        okhttp3.Call localVarCall = updateAlgorithmJarValidateBeforeCall(id, jar, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Replaces algorithm artifact
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Artifact successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmJarAsync(String id, File jar, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateAlgorithmJarValidateBeforeCall(id, jar, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateAlgorithmMeta
     * @param id id of algorithm which metadata is replaced (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmMetaCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = srcMeta;

        // create path and map variables
        String localVarPath = "/algoMeta/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateAlgorithmMetaValidateBeforeCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateAlgorithmMeta(Async)");
        }
        
        // verify the required parameter 'srcMeta' is set
        if (srcMeta == null) {
            throw new ApiException("Missing the required parameter 'srcMeta' when calling updateAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = updateAlgorithmMetaCall(id, srcMeta, _callback);
        return localVarCall;

    }

    /**
     * 
     * Replaces algorithm metadata like description or author
     * @param id id of algorithm which metadata is replaced (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void updateAlgorithmMeta(String id, SrcMeta srcMeta) throws ApiException {
        updateAlgorithmMetaWithHttpInfo(id, srcMeta);
    }

    /**
     * 
     * Replaces algorithm metadata like description or author
     * @param id id of algorithm which metadata is replaced (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> updateAlgorithmMetaWithHttpInfo(String id, SrcMeta srcMeta) throws ApiException {
        okhttp3.Call localVarCall = updateAlgorithmMetaValidateBeforeCall(id, srcMeta, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Replaces algorithm metadata like description or author
     * @param id id of algorithm which metadata is replaced (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Metadata successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / metadata not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmMetaAsync(String id, SrcMeta srcMeta, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateAlgorithmMetaValidateBeforeCall(id, srcMeta, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateAlgorithmStatus
     * @param id id of algorithm which status will be replaced (required)
     * @param srcStatus Status to be uploaded (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmStatusCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = srcStatus;

        // create path and map variables
        String localVarPath = "/algoStatus/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateAlgorithmStatusValidateBeforeCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateAlgorithmStatus(Async)");
        }
        
        // verify the required parameter 'srcStatus' is set
        if (srcStatus == null) {
            throw new ApiException("Missing the required parameter 'srcStatus' when calling updateAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = updateAlgorithmStatusCall(id, srcStatus, _callback);
        return localVarCall;

    }

    /**
     * 
     * Replaces the algorithm status and/or benchmarks
     * @param id id of algorithm which status will be replaced (required)
     * @param srcStatus Status to be uploaded (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void updateAlgorithmStatus(String id, SrcStatus srcStatus) throws ApiException {
        updateAlgorithmStatusWithHttpInfo(id, srcStatus);
    }

    /**
     * 
     * Replaces the algorithm status and/or benchmarks
     * @param id id of algorithm which status will be replaced (required)
     * @param srcStatus Status to be uploaded (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> updateAlgorithmStatusWithHttpInfo(String id, SrcStatus srcStatus) throws ApiException {
        okhttp3.Call localVarCall = updateAlgorithmStatusValidateBeforeCall(id, srcStatus, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Replaces the algorithm status and/or benchmarks
     * @param id id of algorithm which status will be replaced (required)
     * @param srcStatus Status to be uploaded (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully replaced </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code / status not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateAlgorithmStatusAsync(String id, SrcStatus srcStatus, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateAlgorithmStatusValidateBeforeCall(id, srcStatus, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for createAlgorithmCode
     * @param id id of algorithm to upload (required)
     * @param code  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Source code successfully uploaded </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmCodeCall(String id, File code, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoCode/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (code != null) {
            localVarFormParams.put("code", code);
        }

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createAlgorithmCodeValidateBeforeCall(String id, File code, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling createAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = createAlgorithmCodeCall(id, code, _callback);
        return localVarCall;

    }

    /**
     * 
     * Uploads the algorithm source
     * @param id id of algorithm to upload (required)
     * @param code  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Source code successfully uploaded </td><td>  -  </td></tr>
     </table>
     */
    public void createAlgorithmCode(String id, File code) throws ApiException {
        createAlgorithmCodeWithHttpInfo(id, code);
    }

    /**
     * 
     * Uploads the algorithm source
     * @param id id of algorithm to upload (required)
     * @param code  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Source code successfully uploaded </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> createAlgorithmCodeWithHttpInfo(String id, File code) throws ApiException {
        okhttp3.Call localVarCall = createAlgorithmCodeValidateBeforeCall(id, code, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Uploads the algorithm source
     * @param id id of algorithm to upload (required)
     * @param code  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Source code successfully uploaded </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmCodeAsync(String id, File code, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = createAlgorithmCodeValidateBeforeCall(id, code, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for createAlgorithmJar
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Artifact successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmJarCall(String id, File jar, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/algoJar/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (jar != null) {
            localVarFormParams.put("jar", jar);
        }

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createAlgorithmJarValidateBeforeCall(String id, File jar, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling createAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = createAlgorithmJarCall(id, jar, _callback);
        return localVarCall;

    }

    /**
     * 
     * Uploads algorithm artifact
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Artifact successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void createAlgorithmJar(String id, File jar) throws ApiException {
        createAlgorithmJarWithHttpInfo(id, jar);
    }

    /**
     * 
     * Uploads algorithm artifact
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Artifact successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> createAlgorithmJarWithHttpInfo(String id, File jar) throws ApiException {
        okhttp3.Call localVarCall = createAlgorithmJarValidateBeforeCall(id, jar, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Uploads algorithm artifact
     * @param id id of algorithm which jar is uploaded (required)
     * @param jar  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Artifact successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmJarAsync(String id, File jar, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = createAlgorithmJarValidateBeforeCall(id, jar, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for createAlgorithmMeta
     * @param id id of algorithm which metadata is uploaded (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Metadata successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmMetaCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = srcMeta;

        // create path and map variables
        String localVarPath = "/algoMeta/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createAlgorithmMetaValidateBeforeCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling createAlgorithmMeta(Async)");
        }
        
        // verify the required parameter 'srcMeta' is set
        if (srcMeta == null) {
            throw new ApiException("Missing the required parameter 'srcMeta' when calling createAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = createAlgorithmMetaCall(id, srcMeta, _callback);
        return localVarCall;

    }

    /**
     * 
     * Upoads algorithm metadata like description or author
     * @param id id of algorithm which metadata is uploaded (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Metadata successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void createAlgorithmMeta(String id, SrcMeta srcMeta) throws ApiException {
        createAlgorithmMetaWithHttpInfo(id, srcMeta);
    }

    /**
     * 
     * Upoads algorithm metadata like description or author
     * @param id id of algorithm which metadata is uploaded (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Metadata successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> createAlgorithmMetaWithHttpInfo(String id, SrcMeta srcMeta) throws ApiException {
        okhttp3.Call localVarCall = createAlgorithmMetaValidateBeforeCall(id, srcMeta, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Upoads algorithm metadata like description or author
     * @param id id of algorithm which metadata is uploaded (required)
     * @param srcMeta Metadata to be uploaded (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Metadata successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmMetaAsync(String id, SrcMeta srcMeta, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = createAlgorithmMetaValidateBeforeCall(id, srcMeta, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for createAlgorithmStatus
     * @param id id of algorithm which status will be uploaded (required)
     * @param srcStatus Status to be uploaded (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmStatusCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = srcStatus;

        // create path and map variables
        String localVarPath = "/algoStatus/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createAlgorithmStatusValidateBeforeCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling createAlgorithmStatus(Async)");
        }
        
        // verify the required parameter 'srcStatus' is set
        if (srcStatus == null) {
            throw new ApiException("Missing the required parameter 'srcStatus' when calling createAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = createAlgorithmStatusCall(id, srcStatus, _callback);
        return localVarCall;

    }

    /**
     * 
     * Uploads the algorithm status and/or benchmarks
     * @param id id of algorithm which status will be uploaded (required)
     * @param srcStatus Status to be uploaded (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void createAlgorithmStatus(String id, SrcStatus srcStatus) throws ApiException {
        createAlgorithmStatusWithHttpInfo(id, srcStatus);
    }

    /**
     * 
     * Uploads the algorithm status and/or benchmarks
     * @param id id of algorithm which status will be uploaded (required)
     * @param srcStatus Status to be uploaded (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> createAlgorithmStatusWithHttpInfo(String id, SrcStatus srcStatus) throws ApiException {
        okhttp3.Call localVarCall = createAlgorithmStatusValidateBeforeCall(id, srcStatus, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Uploads the algorithm status and/or benchmarks
     * @param id id of algorithm which status will be uploaded (required)
     * @param srcStatus Status to be uploaded (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Status successfully uploaded </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAlgorithmStatusAsync(String id, SrcStatus srcStatus, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = createAlgorithmStatusValidateBeforeCall(id, srcStatus, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
