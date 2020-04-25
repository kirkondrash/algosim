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
@Api(value = "algoJar", description = "the algoJar API",tags={ "artifact" })
public interface AlgoJarApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "createAlgorithmJar", notes = "Uploads algorithm artifact")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Artifact successfully uploaded"),
            @ApiResponse(code = 409, message = "Source code already uploaded for this id")
    })
    @RequestMapping(value = "/algoJar/{id}",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    default ResponseEntity<Void> createAlgorithmJar(@ApiParam(value = "id of algorithm which jar is uploaded",required=true) @PathVariable("id") String id, @ApiParam(value = "file detail") @Valid @RequestPart("jar") MultipartFile jar) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "readAlgorithmJar", notes = "Returns algorithm jar", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Artifact successfully fetched", response = Resource.class),
            @ApiResponse(code = 404, message = "Artifact not found for this id") })
    @RequestMapping(value = "/algoJar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE },
            method = RequestMethod.GET)
    default ResponseEntity<Resource> readAlgorithmJar(@ApiParam(value = "id of algorithm which jar will be fetched",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "updateAlgorithmJar", notes = "Replaces algorithm artifact")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Artifact successfully replaced"),
            @ApiResponse(code = 404, message = "Source code / artifact not found for this id") })
    @RequestMapping(value = "/algoJar/{id}",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.PUT)
    default ResponseEntity<Void> updateAlgorithmJar(@ApiParam(value = "id of algorithm which jar is uploaded",required=true) @PathVariable("id") String id, @ApiParam(value = "file detail") @Valid @RequestPart("jar") MultipartFile jar) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmJar", notes = "deletes the algorithm jar based on the id supplied")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Artifact successfully deleted"),
            @ApiResponse(code = 404, message = "Artifact not found for this id") })
    @RequestMapping(value = "/algoJar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmJar(@ApiParam(value = "id of algorithm to delete",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
