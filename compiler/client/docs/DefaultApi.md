# DefaultApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**compileAlgorithm**](DefaultApi.md#compileAlgorithm) | **POST** /compile/{id} | 


<a name="compileAlgorithm"></a>
# **compileAlgorithm**
> compileAlgorithm(id)



Gets the sources from the db and compiles them

### Example
```java
// Import classes:
import hse.algosim.compiler.client.api.ApiClient;
import hse.algosim.compiler.client.api.ApiException;
import hse.algosim.compiler.client.api.CompilerApiClientInstance;import hse.algosim.compiler.client.api.Configuration;
import hse.algosim.compiler.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    CompilerApiClientInstance apiInstance = new CompilerApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      apiInstance.compileAlgorithm(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#compileAlgorithm");
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
**200** | Successfully compiled |  -  |
**404** | Source code not found for this UUID |  -  |

