# DefaultApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeAlgorithm**](DefaultApi.md#executeAlgorithm) | **POST** /execute/{id} | 


<a name="executeAlgorithm"></a>
# **executeAlgorithm**
> executeAlgorithm(id)



Gets the artifact from nexus, executes and benchmarks it

### Example
```java
// Import classes:
import hse.algosim.executor.client.api.ApiClient;
import hse.algosim.executor.client.api.ApiException;
import hse.algosim.executor.client.api.Configuration;
import hse.algosim.executor.client.api.models.*;
import hse.algosim.executor.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      apiInstance.executeAlgorithm(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#executeAlgorithm");
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
 **id** | [**UUID**](.md)| UUID of algorithm to fetch |

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
**404** | Artifact not found for this UUID |  -  |

