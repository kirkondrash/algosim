package hse.algosim.repo.server.api;

import io.swagger.annotations.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
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
@Api(value = "algoJar", description = "the algoJar API")
public interface AlgoJarApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmJar", notes = "deletes the algorithm jar based on the UUID supplied", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Artifact successfully deleted"),
        @ApiResponse(code = 404, message = "Artifact not found for this UUID") })
    @RequestMapping(value = "/algoJar/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmJar(@ApiParam(value = "UUID of algorithm to delete",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getAlgorithmJar", notes = "Returns algorithm jar", response = Resource.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Artifact successfully fetched", response = Resource.class),
        @ApiResponse(code = 404, message = "Artifact not found for this UUID") })
    @RequestMapping(value = "/algoJar/{id}",
        produces = { "application/octet-stream" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Resource> getAlgorithmJar(@ApiParam(value = "UUID of algorithm which jar will be fetched",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "replaceAlgorithmJar", notes = "Replaces algorithm artifact", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Artifact successfully replaced"),
        @ApiResponse(code = 404, message = "Source code / artifact not found for this UUID") })
    @RequestMapping(value = "/algoJar/{id}",
        consumes = { "multipart/form-data" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> replaceAlgorithmJar(@ApiParam(value = "UUID of algorithm which jar is uploaded",required=true) @PathVariable("id") UUID id,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile jar) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "uploadAlgorithmJar", notes = "Uploads algorithm artifact", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Artifact successfully uploaded"),
        @ApiResponse(code = 404, message = "Source code not found for this UUID") })
    @RequestMapping(value = "/algoJar/{id}",
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> uploadAlgorithmJar(@ApiParam(value = "UUID of algorithm which jar is uploaded",required=true) @PathVariable("id") UUID id,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile jar) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
