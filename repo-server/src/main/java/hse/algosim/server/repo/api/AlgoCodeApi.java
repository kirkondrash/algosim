package hse.algosim.server.repo.api;

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

@Validated
@Api(value = "algoCode", description = "the algoCode API", tags={ "source code"})
public interface AlgoCodeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "createAlgorithmCode", notes = "Uploads the algorithm source")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Source code successfully uploaded"),
            @ApiResponse(code = 409, message = "Source code already uploaded for this id")
    })
    @RequestMapping(value = "/algoCode/{id}",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    default ResponseEntity<Void> createAlgorithmCode(@ApiParam(value = "id of algorithm to upload",required=true) @PathVariable("id") String id, @ApiParam(value = "code", required=true) @Valid @RequestPart("code") MultipartFile code) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "readAlgorithmCode", notes = "Returns algorithm source", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Source code successfully fetched", response = Resource.class),
            @ApiResponse(code = 404, message = "Source code not found for this id")
    })
    @RequestMapping(value = "/algoCode/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE },
            method = RequestMethod.GET)
    default ResponseEntity<Resource> readAlgorithmCode(@ApiParam(value = "id of algorithm to fetch",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "updateAlgorithmCode", notes = "Replaces the algorithm source")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Source code successfully replaced"),
            @ApiResponse(code = 404, message = "Source code not found for this id")
    })
    @RequestMapping(value = "/algoCode/{id}",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.PUT)
    default ResponseEntity<Void> updateAlgorithmCode(@ApiParam(value = "id of algorithm to replace",required=true) @PathVariable("id") String id, @ApiParam(value = "code", required=true) @Valid @RequestPart("code") MultipartFile code) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmCode", notes = "Deletes algorithm source")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Source code successfully deleted"),
            @ApiResponse(code = 404, message = "Source code not found for this id") })
    @RequestMapping(value = "/algoCode/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmCode(@ApiParam(value = "id of algorithm to delete",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
