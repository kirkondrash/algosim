/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.2.1-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package hse.algosim.repo.server.api;

import hse.algosim.repo.server.model.SrcStatus;
import java.util.UUID;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "algoStatus", description = "the algoStatus API")
public interface AlgoStatusApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "changeAlgorithmStatus", notes = "Changes the algorithm status and/or benchmarks", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully changed"),
        @ApiResponse(code = 404, message = "Status not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> changeAlgorithmStatus(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id,@ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "deleteAlgorithmStatus", notes = "Returns an algorithm status", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully deleted"),
        @ApiResponse(code = 404, message = "Status not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmStatus(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "findAlgorithmStatus", notes = "Returns an algorithm status", response = SrcStatus.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "algorithm starus", response = SrcStatus.class),
        @ApiResponse(code = 404, message = "Status not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<SrcStatus> findAlgorithmStatus(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"rank\" : 0,  \"winloss\" : \"+200kk\",  \"status\" : \"SCHEDULED\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "uploadAlgorithmStatus", notes = "Uploads the algorithm status and/or benchmarks", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully changed"),
        @ApiResponse(code = 404, message = "Source code not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> uploadAlgorithmStatus(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id,@ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
