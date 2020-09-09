package hse.algosim.client.executor.api;

import hse.algosim.client.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExecutorApiClientInstance {
    private ApiClient localVarApiClient;

    @Autowired
    public ExecutorApiClientInstance(@Value("${executor.basePath:http://executor:8080/api}") String basePath)  {
        this.localVarApiClient = new ApiClient(
                System.getenv("API_CLIENT_USER"), System.getenv("API_CLIENT_PASSWORD"), basePath);
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for executeAlgorithm
     * @param id id of algorithm to fetch (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully executed </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call executeAlgorithmCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/execute/{id}"
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
    private okhttp3.Call executeAlgorithmValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling executeAlgorithm(Async)");
        }


        okhttp3.Call localVarCall = executeAlgorithmCall(id, _callback);
        return localVarCall;

    }

    /**
     *
     * Gets the artifact from nexus, executes and benchmarks it
     * @param id id of algorithm to fetch (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully executed </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public void executeAlgorithm(String id) throws ApiException {
        executeAlgorithmWithHttpInfo(id);
    }

    /**
     *
     * Gets the artifact from nexus, executes and benchmarks it
     * @param id id of algorithm to fetch (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully executed </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> executeAlgorithmWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = executeAlgorithmValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Gets the artifact from nexus, executes and benchmarks it
     * @param id id of algorithm to fetch (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully executed </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Artifact not found for this id </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call executeAlgorithmAsync(String id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = executeAlgorithmValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
