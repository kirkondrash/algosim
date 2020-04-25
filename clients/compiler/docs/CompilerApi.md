# CompilerApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**compileAlgorithm**](CompilerApi.md#compilealgorithm) | **POST** /compile/{id} | 


<a name="compileAlgorithm"></a>
# **compileAlgorithm**
> compileAlgorithm(id)



Gets the sources from the db and compiles them

### Example
```java
// Import classes:
import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.api.Configuration;
import hse.algosim.client.models.*;
import CompilerApiClientInstance;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    CompilerApiClientInstance apiInstance = new CompilerApiClientInstance(defaultClient);
    String id = "a";
    try {
      apiInstance.compileAlgorithm(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling CompilerApiClientInstance#compileAlgorithm");
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
**200** | Successfully compiled |  -  |
**404** | Source code not found for this id |  -  |

