# RepoApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteAlgorithmCode**](RepoApi.md#deletealgorithmcode) | **DELETE** /algoCode/{id} | 
[**deleteAlgorithmJar**](RepoApi.md#deletealgorithmjar) | **DELETE** /algoJar/{id} | 
[**deleteAlgorithmMeta**](RepoApi.md#deletealgorithmmeta) | **DELETE** /algoMeta/{id} | 
[**deleteAlgorithmStatus**](RepoApi.md#deletealgorithmstatus) | **DELETE** /algoStatus/{id} | 
[**readAlgorithmCode**](RepoApi.md#readAlgorithmCode) | **GET** /algoCode/{id} | 
[**readAlgorithmJar**](RepoApi.md#readAlgorithmJar) | **GET** /algoJar/{id} | 
[**readAlgorithmMeta**](RepoApi.md#readAlgorithmMeta) | **GET** /algoMeta/{id} | 
[**readAlgorithmStatus**](RepoApi.md#readAlgorithmStatus) | **GET** /algoStatus/{id} | 
[**getTopCode**](RepoApi.md#gettopcode) | **GET** /getTopCode | 
[**updateAlgorithmCode**](RepoApi.md#updateAlgorithmCode) | **PUT** /algoCode/{id} | 
[**updateAlgorithmJar**](RepoApi.md#updateAlgorithmJar) | **PUT** /algoJar/{id} | 
[**updateAlgorithmMeta**](RepoApi.md#updateAlgorithmMeta) | **PUT** /algoMeta/{id} | 
[**updateAlgorithmStatus**](RepoApi.md#updateAlgorithmStatus) | **PUT** /algoStatus/{id} | 
[**createAlgorithmCode**](RepoApi.md#createAlgorithmCode) | **POST** /algoCode/{id} | 
[**createAlgorithmJar**](RepoApi.md#createAlgorithmJar) | **POST** /algoJar/{id} | 
[**createAlgorithmMeta**](RepoApi.md#createAlgorithmMeta) | **POST** /algoMeta/{id} | 
[**createAlgorithmStatus**](RepoApi.md#createAlgorithmStatus) | **POST** /algoStatus/{id} | 


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
    String id = "a"; 
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
 **id** | [**String**](.md)| id of algorithm to delete |

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
**404** | Source code not found for this id |  -  |

<a name="deleteAlgorithmJar"></a>
# **deleteAlgorithmJar**
> deleteAlgorithmJar(id)



deletes the algorithm jar based on the id supplied

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
    String id = "a";
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
 **id** | [**String**](.md)| id of algorithm to delete |

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
**404** | Artifact not found for this id |  -  |

<a name="deleteAlgorithmMeta"></a>
# **deleteAlgorithmMeta**
> deleteAlgorithmMeta(id)



Deletes algorithm metadata based on the id supplied

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
    String id = "a"; 
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
 **id** | [**String**](.md)| id of algorithm which metadata will be deleted |

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
**404** | Metadata not found for this id |  -  |

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
    String id = "a";
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
 **id** | [**String**](.md)| id of algorithm which status will be deleted |

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
**404** | Status not found for this id |  -  |

<a name="readAlgorithmCode"></a>
# **readAlgorithmCode**
> File readAlgorithmCode(id)



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
    String id = "a"; 
    try {
      File result = apiInstance.readAlgorithmCode(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#readAlgorithmCode");
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

<a name="readAlgorithmJar"></a>
# **readAlgorithmJar**
> File readAlgorithmJar(id)



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
    String id = "a";
    try {
      File result = apiInstance.readAlgorithmJar(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#readAlgorithmJar");
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
 **id** | [**String**](.md)| id of algorithm which jar will be fetched |

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
**404** | Artifact not found for this id |  -  |

<a name="readAlgorithmMeta"></a>
# **readAlgorithmMeta**
> SrcMeta readAlgorithmMeta(id)



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
    String id = "a"; 
    try {
      SrcMeta result = apiInstance.readAlgorithmMeta(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#readAlgorithmMeta");
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
 **id** | [**String**](.md)| id of algorithm which metadata is fetched |

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
**404** | Metadata not found for this id |  -  |

<a name="readAlgorithmStatus"></a>
# **readAlgorithmStatus**
> SrcStatus readAlgorithmStatus(id)



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
    String id = "a"; 
    try {
      SrcStatus result = apiInstance.readAlgorithmStatus(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#readAlgorithmStatus");
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
 **id** | [**String**](.md)| id of algorithm which status will be fetched |

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
**404** | Status not found for this id |  -  |

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

<a name="updateAlgorithmCode"></a>
# **updateAlgorithmCode**
> updateAlgorithmCode(id, code)



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
    String id = "a";
    File code = new File("/path/to/file"); // File | 
    try {
      apiInstance.updateAlgorithmCode(id, code);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#updateAlgorithmCode");
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
 **id** | [**String**](.md)| id of algorithm to replace |
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

<a name="updateAlgorithmJar"></a>
# **updateAlgorithmJar**
> updateAlgorithmJar(id, jar)



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
    String id = "a";
    File jar = new File("/path/to/file"); // File | 
    try {
      apiInstance.updateAlgorithmJar(id, jar);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#updateAlgorithmJar");
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
 **id** | [**String**](.md)| id of algorithm which jar is uploaded |
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
**404** | Source code / artifact not found for this id |  -  |

<a name="updateAlgorithmMeta"></a>
# **updateAlgorithmMeta**
> updateAlgorithmMeta(id, srcMeta)



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
    String id = "a"; 
    SrcMeta srcMeta = new SrcMeta(); // SrcMeta | Metadata to be uploaded
    try {
      apiInstance.updateAlgorithmMeta(id, srcMeta);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#updateAlgorithmMeta");
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
 **id** | [**String**](.md)| id of algorithm which metadata is replaced |
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
**404** | Source code / metadata not found for this id |  -  |

<a name="updateAlgorithmStatus"></a>
# **updateAlgorithmStatus**
> updateAlgorithmStatus(id, srcStatus)



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
    String id = "a";
    SrcStatus srcStatus = new SrcStatus(); // SrcStatus | Status to be uploaded
    try {
      apiInstance.updateAlgorithmStatus(id, srcStatus);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#updateAlgorithmStatus");
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
 **id** | [**String**](.md)| id of algorithm which status will be replaced |
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
**404** | Source code / status not found for this id |  -  |

<a name="createAlgorithmCode"></a>
# **createAlgorithmCode**
> createAlgorithmCode(id, code)



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
    String id = "a";
    File code = new File("/path/to/file"); // File | 
    try {
      apiInstance.createAlgorithmCode(id, code);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#createAlgorithmCode");
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
 **id** | [**String**](.md)| id of algorithm to upload |
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

<a name="createAlgorithmJar"></a>
# **createAlgorithmJar**
> createAlgorithmJar(id, jar)



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
    String id = "a";
    File jar = new File("/path/to/file"); // File | 
    try {
      apiInstance.createAlgorithmJar(id, jar);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#createAlgorithmJar");
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
 **id** | [**String**](.md)| id of algorithm which jar is uploaded |
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
**404** | Source code not found for this id |  -  |

<a name="createAlgorithmMeta"></a>
# **createAlgorithmMeta**
> createAlgorithmMeta(id, srcMeta)



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
    String id = "a"; 
    SrcMeta srcMeta = new SrcMeta(); // SrcMeta | Metadata to be uploaded
    try {
      apiInstance.createAlgorithmMeta(id, srcMeta);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#createAlgorithmMeta");
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
 **id** | [**String**](.md)| id of algorithm which metadata is uploaded |
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
**404** | Source not found for this id |  -  |

<a name="createAlgorithmStatus"></a>
# **createAlgorithmStatus**
> createAlgorithmStatus(id, srcStatus)



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
    String id = "a"; 
    SrcStatus srcStatus = new SrcStatus(); // SrcStatus | Status to be uploaded
    try {
      apiInstance.createAlgorithmStatus(id, srcStatus);
    } catch (ApiException e) {
      System.err.println("Exception when calling RepoApiClientInstance#createAlgorithmStatus");
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
 **id** | [**String**](.md)| id of algorithm which status will be uploaded |
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
**404** | Source code not found for this id |  -  |

