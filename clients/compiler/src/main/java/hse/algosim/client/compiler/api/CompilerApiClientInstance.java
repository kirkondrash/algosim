package hse.algosim.client.compiler.api;

import hse.algosim.client.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CompilerApiClientInstance {
    private ApiClient localVarApiClient;

    @Autowired
    public CompilerApiClientInstance(@Value("${user.username}") String username, @Value("${user.password}") String password, @Value("${compiler.basePath:http://compiler:8080/api}") String basePath) {
        this.localVarApiClient = new ApiClient(username, password, basePath);
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for compileAlgorithm
     * @param id id of algorithm to fetch (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully compiled </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call compileAlgorithmCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/compile/{id}"
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
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call compileAlgorithmValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling compileAlgorithm(Async)");
        }
        

        okhttp3.Call localVarCall = compileAlgorithmCall(id, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets the sources from the db and compiles them
     * @param id id of algorithm to fetch (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully compiled </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void compileAlgorithm(String id) throws ApiException {
        compileAlgorithmWithHttpInfo(id);
    }

    /**
     * 
     * Gets the sources from the db and compiles them
     * @param id id of algorithm to fetch (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully compiled </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> compileAlgorithmWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = compileAlgorithmValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Gets the sources from the db and compiles them
     * @param id id of algorithm to fetch (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully compiled </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Source code not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call compileAlgorithmAsync(String id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = compileAlgorithmValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
