package hse.algosim.server.repo.api;

import hse.algosim.server.model.SrcMeta;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@Api(value = "algoMeta", description = "the algoMeta API", tags={ "metadata" })
public interface AlgoMetaApi {

    @ApiOperation(value = "", nickname = "createAlgorithmMeta", notes = "Upoads algorithm metadata like description or author")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Metadata successfully uploaded"),
            @ApiResponse(code = 409, message = "Metadata already uploaded for this id")
    })
    @PostMapping(value = "/algoMeta/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<Void> createAlgorithmMeta(@ApiParam(value = "id of algorithm which metadata is uploaded",required=true) @PathVariable("id") String id, @ApiParam(value = "Metadata to be uploaded" ,required=true )  @Valid @RequestBody SrcMeta srcMeta) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "readAlgorithmMeta", notes = "Returns algorithm metadata", response = SrcMeta.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Metadata successfully fetched", response = SrcMeta.class),
            @ApiResponse(code = 404, message = "Metadata not found for this id")
    })
    @GetMapping(value = "/algoMeta/{id}",  produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<SrcMeta> readAlgorithmMeta(@ApiParam(value = "id of algorithm which metadata is fetched",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "updateAlgorithmMeta", notes = "Replaces algorithm metadata like description or author")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Metadata successfully replaced"),
            @ApiResponse(code = 404, message = "Source code / metadata not found for this id")
    })
    @PutMapping(value = "/algoMeta/{id}",  consumes = { MediaType.APPLICATION_JSON_VALUE },  produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<Void> updateAlgorithmMeta(@ApiParam(value = "id of algorithm which metadata is replaced",required=true) @PathVariable("id") String id, @ApiParam(value = "Metadata to be uploaded" ,required=true )  @Valid @RequestBody SrcMeta srcMeta) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmMeta", notes = "Deletes algorithm metadata based on the id supplied")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Metadata successfully deleted"),
            @ApiResponse(code = 404, message = "Metadata not found for this id")
    })
    @DeleteMapping(value = "/algoMeta/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<Void> deleteAlgorithmMeta(@ApiParam(value = "id of algorithm which metadata will be deleted",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
