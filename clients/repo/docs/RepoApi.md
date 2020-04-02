# RepoApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteAlgorithmCode**](RepoApi.md#deletealgorithmcode) | **DELETE** /algoCode/{id} | 
[**deleteAlgorithmJar**](RepoApi.md#deletealgorithmjar) | **DELETE** /algoJar/{id} | 
[**deleteAlgorithmMeta**](RepoApi.md#deletealgorithmmeta) | **DELETE** /algoMeta/{id} | 
[**deleteAlgorithmStatus**](RepoApi.md#deletealgorithmstatus) | **DELETE** /algoStatus/{id} | 
[**getAlgorithmCode**](RepoApi.md#getalgorithmcode) | **GET** /algoCode/{id} | 
[**getAlgorithmJar**](RepoApi.md#getalgorithmjar) | **GET** /algoJar/{id} | 
[**getAlgorithmMeta**](RepoApi.md#getalgorithmmeta) | **GET** /algoMeta/{id} | 
[**getAlgorithmStatus**](RepoApi.md#getalgorithmstatus) | **GET** /algoStatus/{id} | 
[**getTopCode**](RepoApi.md#gettopcode) | **GET** /getTopCode | 
[**replaceAlgorithmCode**](RepoApi.md#replacealgorithmcode) | **PUT** /algoCode/{id} | 
[**replaceAlgorithmJar**](RepoApi.md#replacealgorithmjar) | **PUT** /algoJar/{id} | 
[**replaceAlgorithmMeta**](RepoApi.md#replacealgorithmmeta) | **PUT** /algoMeta/{id} | 
[**replaceAlgorithmStatus**](RepoApi.md#replacealgorithmstatus) | **PUT** /algoStatus/{id} | 
[**uploadAlgorithmCode**](RepoApi.md#uploadalgorithmcode) | **POST** /algoCode/{id} | 
[**uploadAlgorithmJar**](RepoApi.md#uploadalgorithmjar) | **POST** /algoJar/{id} | 
[**uploadAlgorithmMeta**](RepoApi.md#uploadalgorithmmeta) | **POST** /algoMeta/{id} | 
[**uploadAlgorithmStatus**](RepoApi.md#uploadalgorithmstatus) | **POST** /algoStatus/{id} | 


<a name="deleteAlgorithmCode"></a>
# **deleteAlgorithmCode**
> deleteAlgorithmCode(id)



Deletes algorithm source

### Example
```java
// Import classes:
import hse.algosim.client.api.ApiClient;
import hse.algosim.client.api.ApiException;
import hse.algosim.client.api.Configuration;
import hse.algosim.client.models.*;
import RepoApiClientInstance;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to delete
    try {
      apiInstance.deleteAlgorithmCode(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#deleteAlgorithmCode");
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
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to delete
    try {
      apiInstance.deleteAlgorithmJar(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#deleteAlgorithmJar");
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



Deletes algorithm metadata based on the UUID supplied

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.RepoApiClientInstance;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata will be deleted
    try {
      apiInstance.deleteAlgorithmMeta(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#deleteAlgorithmMeta");
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
 **id** | [**UUID**](.md)| UUID of algorithm which metadata will be deleted |

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
**204** | Metadata successfully deleted |  -  |
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
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which status will be deleted
    try {
      apiInstance.deleteAlgorithmStatus(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#deleteAlgorithmStatus");
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
 **id** | [**UUID**](.md)| UUID of algorithm which status will be deleted |

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

<a name="getAlgorithmCode"></a>
# **getAlgorithmCode**
> File getAlgorithmCode(id)



Returns algorithm source

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to fetch
    try {
      File result = apiInstance.getAlgorithmCode(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#getAlgorithmCode");
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
**200** | Source code successfully fetched |  -  |
**404** | Source code not found for this UUI |  -  |

<a name="getAlgorithmJar"></a>
# **getAlgorithmJar**
> File getAlgorithmJar(id)



Returns algorithm jar

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.RepoApiClientInstance;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which jar will be fetched
    try {
      File result = apiInstance.getAlgorithmJar(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#getAlgorithmJar");
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
 **id** | [**UUID**](.md)| UUID of algorithm which jar will be fetched |

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
**200** | Artifact successfully fetched |  -  |
**404** | Artifact not found for this UUID |  -  |

<a name="getAlgorithmMeta"></a>
# **getAlgorithmMeta**
> SrcMeta getAlgorithmMeta(id)



Returns algorithm metadata

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata is fetched
    try {
      SrcMeta result = apiInstance.getAlgorithmMeta(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#getAlgorithmMeta");
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

[**SrcMeta**](../../core/docs/SrcMeta.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Metadata successfully fetched |  -  |
**404** | Metadata not found for this UUID |  -  |

<a name="getAlgorithmStatus"></a>
# **getAlgorithmStatus**
> SrcStatus getAlgorithmStatus(id)



Returns an algorithm status

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which status will be fetched
    try {
      SrcStatus result = apiInstance.getAlgorithmStatus(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#getAlgorithmStatus");
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
 **id** | [**UUID**](.md)| UUID of algorithm which status will be fetched |

### Return type

[**SrcStatus**](../../core/docs/SrcStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Status successfully fetched |  -  |
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
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    try {
      IdArray result = apiInstance.getTopCode();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#getTopCode");
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

[**IdArray**](../../core/docs/IdArray.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Source code |  -  |

<a name="replaceAlgorithmCode"></a>
# **replaceAlgorithmCode**
> replaceAlgorithmCode(id, code)



Replaces the algorithm source

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to replace
    File code = new File("/path/to/file"); // File | 
    try {
      apiInstance.replaceAlgorithmCode(id, code);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#replaceAlgorithmCode");
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
 **id** | [**UUID**](.md)| UUID of algorithm to replace |
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
**200** | Source code successfully replaced |  -  |

<a name="replaceAlgorithmJar"></a>
# **replaceAlgorithmJar**
> replaceAlgorithmJar(id, jar)



Replaces algorithm artifact

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which jar is uploaded
    File jar = new File("/path/to/file"); // File | 
    try {
      apiInstance.replaceAlgorithmJar(id, jar);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#replaceAlgorithmJar");
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
**200** | Artifact successfully replaced |  -  |
**404** | Source code / artifact not found for this UUID |  -  |

<a name="replaceAlgorithmMeta"></a>
# **replaceAlgorithmMeta**
> replaceAlgorithmMeta(id, srcMeta)



Replaces algorithm metadata like description or author

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata is replaced
    SrcMeta srcMeta = new SrcMeta(); // SrcMeta | Metadata to be uploaded
    try {
      apiInstance.replaceAlgorithmMeta(id, srcMeta);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#replaceAlgorithmMeta");
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
 **id** | [**UUID**](.md)| UUID of algorithm which metadata is replaced |
 **srcMeta** | [**SrcMeta**](../../core/docs/SrcMeta.md)| Metadata to be uploaded |

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
**200** | Metadata successfully replaced |  -  |
**404** | Source code / metadata not found for this UUID |  -  |

<a name="replaceAlgorithmStatus"></a>
# **replaceAlgorithmStatus**
> replaceAlgorithmStatus(id, srcStatus)



Replaces the algorithm status and/or benchmarks

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.models.*;
import hse.algosim.repo.client.api.RepoApiClientInstance;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which status will be replaced
    SrcStatus srcStatus = new SrcStatus(); // SrcStatus | Status to be uploaded
    try {
      apiInstance.replaceAlgorithmStatus(id, srcStatus);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#replaceAlgorithmStatus");
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
 **id** | [**UUID**](.md)| UUID of algorithm which status will be replaced |
 **srcStatus** | [**SrcStatus**](../../core/docs/SrcStatus.md)| Status to be uploaded |

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
**200** | Status successfully replaced |  -  |
**404** | Source code / status not found for this UUID |  -  |

<a name="uploadAlgorithmCode"></a>
# **uploadAlgorithmCode**
> uploadAlgorithmCode(id, code)



Uploads the algorithm source

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm to upload
    File code = new File("/path/to/file"); // File | 
    try {
      apiInstance.uploadAlgorithmCode(id, code);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#uploadAlgorithmCode");
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
 **id** | [**UUID**](.md)| UUID of algorithm to upload |
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
**201** | Source code successfully uploaded |  -  |

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
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which jar is uploaded
    File jar = new File("/path/to/file"); // File | 
    try {
      apiInstance.uploadAlgorithmJar(id, jar);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#uploadAlgorithmJar");
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
**201** | Artifact successfully uploaded |  -  |
**404** | Source code not found for this UUID |  -  |

<a name="uploadAlgorithmMeta"></a>
# **uploadAlgorithmMeta**
> uploadAlgorithmMeta(id, srcMeta)



Upoads algorithm metadata like description or author

### Example
```java
// Import classes:
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.Configuration;
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which metadata is uploaded
    SrcMeta srcMeta = new SrcMeta(); // SrcMeta | Metadata to be uploaded
    try {
      apiInstance.uploadAlgorithmMeta(id, srcMeta);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#uploadAlgorithmMeta");
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
 **srcMeta** | [**SrcMeta**](../../core/docs/SrcMeta.md)| Metadata to be uploaded |

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
**201** | Metadata successfully uploaded |  -  |
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
import hse.algosim.repo.client.api.RepoApiClientInstance;import hse.algosim.repo.client.api.models.*;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    RepoApiClientInstance apiInstance = new RepoApiClientInstance(defaultClient);
    UUID id = new UUID(); // UUID | UUID of algorithm which status will be uploaded
    SrcStatus srcStatus = new SrcStatus(); // SrcStatus | Status to be uploaded
    try {
      apiInstance.uploadAlgorithmStatus(id, srcStatus);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#uploadAlgorithmStatus");
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
 **id** | [**UUID**](.md)| UUID of algorithm which status will be uploaded |
 **srcStatus** | [**SrcStatus**](../../core/docs/SrcStatus.md)| Status to be uploaded |

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
**200** | Status successfully uploaded |  -  |
**404** | Source code not found for this UUID |  -  |

