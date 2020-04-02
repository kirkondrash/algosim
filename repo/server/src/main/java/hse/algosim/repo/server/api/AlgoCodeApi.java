package hse.algosim.repo.server.api;

import hse.algosim.server.model.IdArray;
import io.swagger.annotations.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Validated
@Api(value = "algoCode", description = "the algoCode API")
public interface AlgoCodeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmCode", notes = "Deletes algorithm source", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Source code successfully deleted"),
        @ApiResponse(code = 404, message = "Source code not found for this UUID") })
    @RequestMapping(value = "/algoCode/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmCode(@ApiParam(value = "UUID of algorithm to delete",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getAlgorithmCode", notes = "Returns algorithm source", response = Resource.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Source code successfully fetched", response = Resource.class),
        @ApiResponse(code = 404, message = "Source code not found for this UUI") })
    @RequestMapping(value = "/algoCode/{id}",
        produces = { "application/octet-stream" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Resource> getAlgorithmCode(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "replaceAlgorithmCode", notes = "Replaces the algorithm source", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Source code successfully replaced") })
    @RequestMapping(value = "/algoCode/{id}",
        consumes = { "multipart/form-data" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> replaceAlgorithmCode(@ApiParam(value = "UUID of algorithm to replace",required=true) @PathVariable("id") UUID id,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile code) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "uploadAlgorithmCode", notes = "Uploads the algorithm source", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Source code successfully uploaded") })
    @RequestMapping(value = "/algoCode/{id}",
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> uploadAlgorithmCode(@ApiParam(value = "UUID of algorithm to upload",required=true) @PathVariable("id") UUID id,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile code) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "", nickname = "getTopCode", notes = "Returns some 10 algos ids", response = IdArray.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Source code ids", response = IdArray.class) })
    @RequestMapping(value = "/getTopCode",
            produces = { "application/json" },
            method = RequestMethod.GET)
    default ResponseEntity<IdArray> getTopCode() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : [ \"id\", \"id\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
