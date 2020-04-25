package hse.algosim.client.repo.api;

import com.google.gson.reflect.TypeToken;
import hse.algosim.client.api.*;
import hse.algosim.client.model.IdArray;
import hse.algosim.client.model.SrcMeta;
import hse.algosim.client.model.SrcStatus;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class RepoApiClientInstance {
    private ApiClient localVarApiClient;

    public RepoApiClientInstance() {
        this(Configuration.getDefaultApiClient());
    }

    public RepoApiClientInstance(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
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
     * Build call for getAlgorithmCode
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
    public okhttp3.Call getAlgorithmCodeCall(String id, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call getAlgorithmCodeValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = getAlgorithmCodeCall(id, _callback);
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
    public File getAlgorithmCode(String id) throws ApiException {
        ApiResponse<File> localVarResp = getAlgorithmCodeWithHttpInfo(id);
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
    public ApiResponse<File> getAlgorithmCodeWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = getAlgorithmCodeValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
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
    public okhttp3.Call getAlgorithmCodeAsync(String id, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAlgorithmCodeValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAlgorithmJar
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
    public okhttp3.Call getAlgorithmJarCall(String id, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call getAlgorithmJarValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = getAlgorithmJarCall(id, _callback);
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
    public File getAlgorithmJar(String id) throws ApiException {
        ApiResponse<File> localVarResp = getAlgorithmJarWithHttpInfo(id);
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
    public ApiResponse<File> getAlgorithmJarWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = getAlgorithmJarValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
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
    public okhttp3.Call getAlgorithmJarAsync(String id, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAlgorithmJarValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAlgorithmMeta
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
    public okhttp3.Call getAlgorithmMetaCall(String id, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call getAlgorithmMetaValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = getAlgorithmMetaCall(id, _callback);
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
    public SrcMeta getAlgorithmMeta(String id) throws ApiException {
        ApiResponse<SrcMeta> localVarResp = getAlgorithmMetaWithHttpInfo(id);
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
    public ApiResponse<SrcMeta> getAlgorithmMetaWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = getAlgorithmMetaValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<SrcMeta>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
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
    public okhttp3.Call getAlgorithmMetaAsync(String id, final ApiCallback<SrcMeta> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAlgorithmMetaValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<SrcMeta>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAlgorithmStatus
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
    public okhttp3.Call getAlgorithmStatusCall(String id, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call getAlgorithmStatusValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = getAlgorithmStatusCall(id, _callback);
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
    public SrcStatus getAlgorithmStatus(String id) throws ApiException {
        ApiResponse<SrcStatus> localVarResp = getAlgorithmStatusWithHttpInfo(id);
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
    public ApiResponse<SrcStatus> getAlgorithmStatusWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = getAlgorithmStatusValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<SrcStatus>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
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
    public okhttp3.Call getAlgorithmStatusAsync(String id, final ApiCallback<SrcStatus> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAlgorithmStatusValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<SrcStatus>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
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
        Type localVarReturnType = new TypeToken<IdArray>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
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
        Type localVarReturnType = new TypeToken<IdArray>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for replaceAlgorithmCode
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
    public okhttp3.Call replaceAlgorithmCodeCall(String id, File code, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call replaceAlgorithmCodeValidateBeforeCall(String id, File code, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling replaceAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = replaceAlgorithmCodeCall(id, code, _callback);
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
    public void replaceAlgorithmCode(String id, File code) throws ApiException {
        replaceAlgorithmCodeWithHttpInfo(id, code);
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
    public ApiResponse<Void> replaceAlgorithmCodeWithHttpInfo(String id, File code) throws ApiException {
        okhttp3.Call localVarCall = replaceAlgorithmCodeValidateBeforeCall(id, code, null);
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
    public okhttp3.Call replaceAlgorithmCodeAsync(String id, File code, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = replaceAlgorithmCodeValidateBeforeCall(id, code, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for replaceAlgorithmJar
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
    public okhttp3.Call replaceAlgorithmJarCall(String id, File jar, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call replaceAlgorithmJarValidateBeforeCall(String id, File jar, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling replaceAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = replaceAlgorithmJarCall(id, jar, _callback);
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
    public void replaceAlgorithmJar(String id, File jar) throws ApiException {
        replaceAlgorithmJarWithHttpInfo(id, jar);
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
    public ApiResponse<Void> replaceAlgorithmJarWithHttpInfo(String id, File jar) throws ApiException {
        okhttp3.Call localVarCall = replaceAlgorithmJarValidateBeforeCall(id, jar, null);
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
    public okhttp3.Call replaceAlgorithmJarAsync(String id, File jar, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = replaceAlgorithmJarValidateBeforeCall(id, jar, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for replaceAlgorithmMeta
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
    public okhttp3.Call replaceAlgorithmMetaCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call replaceAlgorithmMetaValidateBeforeCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling replaceAlgorithmMeta(Async)");
        }
        
        // verify the required parameter 'srcMeta' is set
        if (srcMeta == null) {
            throw new ApiException("Missing the required parameter 'srcMeta' when calling replaceAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = replaceAlgorithmMetaCall(id, srcMeta, _callback);
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
    public void replaceAlgorithmMeta(String id, SrcMeta srcMeta) throws ApiException {
        replaceAlgorithmMetaWithHttpInfo(id, srcMeta);
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
    public ApiResponse<Void> replaceAlgorithmMetaWithHttpInfo(String id, SrcMeta srcMeta) throws ApiException {
        okhttp3.Call localVarCall = replaceAlgorithmMetaValidateBeforeCall(id, srcMeta, null);
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
    public okhttp3.Call replaceAlgorithmMetaAsync(String id, SrcMeta srcMeta, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = replaceAlgorithmMetaValidateBeforeCall(id, srcMeta, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for replaceAlgorithmStatus
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
    public okhttp3.Call replaceAlgorithmStatusCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call replaceAlgorithmStatusValidateBeforeCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling replaceAlgorithmStatus(Async)");
        }
        
        // verify the required parameter 'srcStatus' is set
        if (srcStatus == null) {
            throw new ApiException("Missing the required parameter 'srcStatus' when calling replaceAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = replaceAlgorithmStatusCall(id, srcStatus, _callback);
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
    public void replaceAlgorithmStatus(String id, SrcStatus srcStatus) throws ApiException {
        replaceAlgorithmStatusWithHttpInfo(id, srcStatus);
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
    public ApiResponse<Void> replaceAlgorithmStatusWithHttpInfo(String id, SrcStatus srcStatus) throws ApiException {
        okhttp3.Call localVarCall = replaceAlgorithmStatusValidateBeforeCall(id, srcStatus, null);
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
    public okhttp3.Call replaceAlgorithmStatusAsync(String id, SrcStatus srcStatus, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = replaceAlgorithmStatusValidateBeforeCall(id, srcStatus, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for uploadAlgorithmCode
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
    public okhttp3.Call uploadAlgorithmCodeCall(String id, File code, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call uploadAlgorithmCodeValidateBeforeCall(String id, File code, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling uploadAlgorithmCode(Async)");
        }
        

        okhttp3.Call localVarCall = uploadAlgorithmCodeCall(id, code, _callback);
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
    public void uploadAlgorithmCode(String id, File code) throws ApiException {
        uploadAlgorithmCodeWithHttpInfo(id, code);
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
    public ApiResponse<Void> uploadAlgorithmCodeWithHttpInfo(String id, File code) throws ApiException {
        okhttp3.Call localVarCall = uploadAlgorithmCodeValidateBeforeCall(id, code, null);
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
    public okhttp3.Call uploadAlgorithmCodeAsync(String id, File code, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = uploadAlgorithmCodeValidateBeforeCall(id, code, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for uploadAlgorithmJar
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
    public okhttp3.Call uploadAlgorithmJarCall(String id, File jar, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call uploadAlgorithmJarValidateBeforeCall(String id, File jar, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling uploadAlgorithmJar(Async)");
        }
        

        okhttp3.Call localVarCall = uploadAlgorithmJarCall(id, jar, _callback);
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
    public void uploadAlgorithmJar(String id, File jar) throws ApiException {
        uploadAlgorithmJarWithHttpInfo(id, jar);
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
    public ApiResponse<Void> uploadAlgorithmJarWithHttpInfo(String id, File jar) throws ApiException {
        okhttp3.Call localVarCall = uploadAlgorithmJarValidateBeforeCall(id, jar, null);
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
    public okhttp3.Call uploadAlgorithmJarAsync(String id, File jar, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = uploadAlgorithmJarValidateBeforeCall(id, jar, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for uploadAlgorithmMeta
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
    public okhttp3.Call uploadAlgorithmMetaCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call uploadAlgorithmMetaValidateBeforeCall(String id, SrcMeta srcMeta, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling uploadAlgorithmMeta(Async)");
        }
        
        // verify the required parameter 'srcMeta' is set
        if (srcMeta == null) {
            throw new ApiException("Missing the required parameter 'srcMeta' when calling uploadAlgorithmMeta(Async)");
        }
        

        okhttp3.Call localVarCall = uploadAlgorithmMetaCall(id, srcMeta, _callback);
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
    public void uploadAlgorithmMeta(String id, SrcMeta srcMeta) throws ApiException {
        uploadAlgorithmMetaWithHttpInfo(id, srcMeta);
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
    public ApiResponse<Void> uploadAlgorithmMetaWithHttpInfo(String id, SrcMeta srcMeta) throws ApiException {
        okhttp3.Call localVarCall = uploadAlgorithmMetaValidateBeforeCall(id, srcMeta, null);
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
    public okhttp3.Call uploadAlgorithmMetaAsync(String id, SrcMeta srcMeta, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = uploadAlgorithmMetaValidateBeforeCall(id, srcMeta, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for uploadAlgorithmStatus
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
    public okhttp3.Call uploadAlgorithmStatusCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call uploadAlgorithmStatusValidateBeforeCall(String id, SrcStatus srcStatus, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling uploadAlgorithmStatus(Async)");
        }
        
        // verify the required parameter 'srcStatus' is set
        if (srcStatus == null) {
            throw new ApiException("Missing the required parameter 'srcStatus' when calling uploadAlgorithmStatus(Async)");
        }
        

        okhttp3.Call localVarCall = uploadAlgorithmStatusCall(id, srcStatus, _callback);
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
    public void uploadAlgorithmStatus(String id, SrcStatus srcStatus) throws ApiException {
        uploadAlgorithmStatusWithHttpInfo(id, srcStatus);
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
    public ApiResponse<Void> uploadAlgorithmStatusWithHttpInfo(String id, SrcStatus srcStatus) throws ApiException {
        okhttp3.Call localVarCall = uploadAlgorithmStatusValidateBeforeCall(id, srcStatus, null);
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
    public okhttp3.Call uploadAlgorithmStatusAsync(String id, SrcStatus srcStatus, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = uploadAlgorithmStatusValidateBeforeCall(id, srcStatus, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
