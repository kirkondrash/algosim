# Executor Api

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeAlgorithm**](ExecutorApi.md#executealgorithm) | **POST** /execute/{id} | 


<a name="executeAlgorithm"></a>
# **executeAlgorithm**
> executeAlgorithm(id)



Gets the artifact from nexus, executes and benchmarks it

### Example
```java
// Import classes:
import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.api.Configuration;
import hse.algosim.server.model.*;
import ExecutorApiClientInstance;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    ExecutorApiClientInstance apiInstance = new ExecutorApiClientInstance(defaultClient);
    String id = "a"; 
    try {
      apiInstance.executeAlgorithm(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExecutorApiClientInstance#executeAlgorithm");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**String**](.md)| id of algorithm to fetch |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully executed |  -  |
**404** | Artifact not found for this id |  -  |

