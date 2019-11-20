/*
 * Algosim repo
 * A repo service for collab project of HSE and Deutsche Bank aiming to improve trading algorithms testing. Service linked to:   - the db for algorithm description, source code and benchmarks   - nexus for the artifacts    
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: kirkondrash@yandex.ru
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package hse.algosim.repo.client.api.auth;

import hse.algosim.repo.client.api.Pair;

import java.util.Map;
import java.util.List;

public interface Authentication {
    /**
     * Apply authentication settings to header and query params.
     *
     * @param queryParams List of query parameters
     * @param headerParams Map of header parameters
     * @param cookieParams Map of cookie parameters
     */
    void applyToParams(List<Pair> queryParams, Map<String, String> headerParams, Map<String, String> cookieParams);
}
