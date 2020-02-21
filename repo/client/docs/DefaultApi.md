# DefaultApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**changeAlgorithmStatus**](DefaultApi.md#changeAlgorithmStatus) | **PUT** /algoStatus/{id} | 
[**deleteAlgorithmCode**](DefaultApi.md#deleteAlgorithmCode) | **DELETE** /algoCode/{id} | 
[**deleteAlgorithmJar**](DefaultApi.md#deleteAlgorithmJar) | **DELETE** /algoJar/{id} | 
[**deleteAlgorithmMeta**](DefaultApi.md#deleteAlgorithmMeta) | **DELETE** /algoMeta/{id} | 
[**deleteAlgorithmStatus**](DefaultApi.md#deleteAlgorithmStatus) | **DELETE** /algoStatus/{id} | 
[**findAlgorithmCode**](DefaultApi.md#findAlgorithmCode) | **GET** /algoCode/{id} | 
[**findAlgorithmJar**](DefaultApi.md#findAlgorithmJar) | **GET** /algoJar/{id} | 
[**findAlgorithmMeta**](DefaultApi.md#findAlgorithmMeta) | **GET** /algoMeta/{id} | 
[**findAlgorithmStatus**](DefaultApi.md#findAlgorithmStatus) | **GET** /algoStatus/{id} | 
[**getTopCode**](DefaultApi.md#getTopCode) | **GET** /getTopCode | 
[**uploadAlgorithmCode**](DefaultApi.md#uploadAlgorithmCode) | **POST** /algoCode/{id} | 
[**uploadAlgorithmJar**](DefaultApi.md#uploadAlgorithmJar) | **POST** /algoJar/{id} | 
[**uploadAlgorithmMeta**](DefaultApi.md#uploadAlgorithmMeta) | **POST** /algoMeta/{id} | 
[**uploadAlgorithmStatus**](DefaultApi.md#uploadAlgorithmStatus) | **POST** /algoStatus/{id} | 


<a name="changeAlgorithmStatus"></a>
# **changeAlgorithmStatus**
> changeAlgorithmStatus(id, srcStatus)



Changes the algorithm status and/or benchmarks

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    SrcStatus srcStatus = new SrcStatus(); // SrcStatus | Status to be uploaded
    try {
      apiInstance.changeAlgorithmStatus(id, srcStatus);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#changeAlgorithmStatus");
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
 **srcStatus** | [**SrcStatus**](SrcStatus.md)| Status to be uploaded |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Status successfully changed |  -  |
**404** | Status not found for this UUID |  -  |

<a name="deleteAlgorithmCode"></a>
# **deleteAlgorithmCode**
> deleteAlgorithmCode(id)



Deletes algorithm sources based on the UUID supplied

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to delete
    try {
      apiInstance.deleteAlgorithmCode(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#deleteAlgorithmCode");
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
 **id** | [**UUID**](.md)| UUID of algorithm to delete |

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
**204** | Source code successfully deleted |  -  |
**404** | Source code not found for this UUID |  -  |

<a name="deleteAlgorithmJar"></a>
# **deleteAlgorithmJar**
> deleteAlgorithmJar(id)



deletes the algorithm jar based on the UUID supplied

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to delete
    try {
      apiInstance.deleteAlgorithmJar(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#deleteAlgorithmJar");
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
 **id** | [**UUID**](.md)| UUID of algorithm to delete |

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
**204** | Artifact successfully deleted |  -  |
**404** | Artifact not found for this UUID |  -  |

<a name="deleteAlgorithmMeta"></a>
# **deleteAlgorithmMeta**
> deleteAlgorithmMeta(id)



Clears algorithm metadata

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata will be cleared
    try {
      apiInstance.deleteAlgorithmMeta(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#deleteAlgorithmMeta");
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
 **id** | [**UUID**](.md)| UUID of algorithm which metadata will be cleared |

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
**204** | Metadata successfully cleared |  -  |
**404** | Metadata not found for this UUID |  -  |

<a name="deleteAlgorithmStatus"></a>
# **deleteAlgorithmStatus**
> deleteAlgorithmStatus(id)



Returns an algorithm status

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      apiInstance.deleteAlgorithmStatus(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#deleteAlgorithmStatus");
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
**200** | Status successfully deleted |  -  |
**404** | Status not found for this UUID |  -  |

<a name="findAlgorithmCode"></a>
# **findAlgorithmCode**
> File findAlgorithmCode(id)



Returns algorithm sources based on UUID supplied

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      File result = apiInstance.findAlgorithmCode(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#findAlgorithmCode");
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

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Source code |  -  |
**404** | Source code not found for this UUID supplied |  -  |

<a name="findAlgorithmJar"></a>
# **findAlgorithmJar**
> File findAlgorithmJar(id)



Returns algorithm jar

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      File result = apiInstance.findAlgorithmJar(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#findAlgorithmJar");
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

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | source code and description |  -  |
**404** | Artifact not found for this UUID |  -  |

<a name="findAlgorithmMeta"></a>
# **findAlgorithmMeta**
> SrcMeta findAlgorithmMeta(id)



Returns algorithm metadata

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata is fetched
    try {
      SrcMeta result = apiInstance.findAlgorithmMeta(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#findAlgorithmMeta");
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
 **id** | [**UUID**](.md)| UUID of algorithm which metadata is fetched |

### Return type

[**SrcMeta**](SrcMeta.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Metadata |  -  |
**404** | Metadata not found for this UUID |  -  |

<a name="findAlgorithmStatus"></a>
# **findAlgorithmStatus**
> SrcStatus findAlgorithmStatus(id)



Returns an algorithm status

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      SrcStatus result = apiInstance.findAlgorithmStatus(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#findAlgorithmStatus");
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

[**SrcStatus**](SrcStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | algorithm starus |  -  |
**404** | Status not found for this UUID |  -  |

<a name="getTopCode"></a>
# **getTopCode**
> IdArray getTopCode()



Returns some 10 algos ids

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      IdArray result = apiInstance.getTopCode();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getTopCode");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**IdArray**](IdArray.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Source code |  -  |

<a name="uploadAlgorithmCode"></a>
# **uploadAlgorithmCode**
> uploadAlgorithmCode(id, code)



Uploads the algorithm sources based on UUID supplied

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to upload to db
    File code = new File("/path/to/file"); // File | 
    try {
      apiInstance.uploadAlgorithmCode(id, code);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#uploadAlgorithmCode");
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
 **id** | [**UUID**](.md)| UUID of algorithm to upload to db |
 **code** | **File**|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Source code was uploaded successfully |  -  |

<a name="uploadAlgorithmJar"></a>
# **uploadAlgorithmJar**
> uploadAlgorithmJar(id, jar)



Uploads algorithm artifact

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which jar is uploaded
    File jar = new File("/path/to/file"); // File | 
    try {
      apiInstance.uploadAlgorithmJar(id, jar);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#uploadAlgorithmJar");
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
 **id** | [**UUID**](.md)| UUID of algorithm which jar is uploaded |
 **jar** | **File**|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully uploaded to nexus |  -  |
**404** | Source code not found for this UUID |  -  |

<a name="uploadAlgorithmMeta"></a>
# **uploadAlgorithmMeta**
> uploadAlgorithmMeta(id, srcMeta)



Loads algorithm metadata like description or author

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata is uploaded
    SrcMeta srcMeta = new SrcMeta(); // SrcMeta | Metadata to be uploaded
    try {
      apiInstance.uploadAlgorithmMeta(id, srcMeta);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#uploadAlgorithmMeta");
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
 **id** | [**UUID**](.md)| UUID of algorithm which metadata is uploaded |
 **srcMeta** | [**SrcMeta**](SrcMeta.md)| Metadata to be uploaded |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | The metadata was uploaded successfully |  -  |
**404** | Source not found for this UUID |  -  |

<a name="uploadAlgorithmStatus"></a>
# **uploadAlgorithmStatus**
> uploadAlgorithmStatus(id, srcStatus)



Uploads the algorithm status and/or benchmarks

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    SrcStatus srcStatus = new SrcStatus(); // SrcStatus | Status to be uploaded
    try {
      apiInstance.uploadAlgorithmStatus(id, srcStatus);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#uploadAlgorithmStatus");
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
 **srcStatus** | [**SrcStatus**](SrcStatus.md)| Status to be uploaded |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Status successfully changed |  -  |
**404** | Source code not found for this UUID |  -  |

