package hse.algosim.server.repo.api;

import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;

@Valid
@Api(value = "algoStatus", description = "the algoStatus API", tags={ "status" })
public interface AlgoStatusApi {

    @ApiOperation(value = "", nickname = "createAlgorithmStatus", notes = "Uploads the algorithm status and/or benchmarks")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Status successfully uploaded"),
            @ApiResponse(code = 409, message = "Source code not found for this id")
    })
    @PostMapping(value = "/algoStatus/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<Void> createAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be uploaded",required=true) @PathVariable("id") String id, @ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "readAlgorithmStatus", notes = "Returns an algorithm status", response = SrcStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status successfully fetched", response = SrcStatus.class),
            @ApiResponse(code = 404, message = "Status not found for this id") })
    @GetMapping(value = "/algoStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<SrcStatus> readAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be fetched",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "updateAlgorithmStatus", notes = "Replaces the algorithm status and/or benchmarks")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Status successfully replaced"),
            @ApiResponse(code = 404, message = "Source code / status not found for this id") })
    @PutMapping(value = "/algoStatus/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<Void> updateAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be replaced",required=true) @PathVariable("id") String id, @ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmStatus", notes = "Returns an algorithm status")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Status successfully deleted"),
            @ApiResponse(code = 404, message = "Status not found for this id") })
    @DeleteMapping(value = "/algoStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<Void> deleteAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be deleted",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "getTopCode", notes = "Returns top 10 algos ids", response = IdArray.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Source code ids", response = IdArray.class) })
    @GetMapping(value = "/getTopCode", produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<IdArray> getTopCode() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "statusUpdateSSE", notes = "Returns updates of statuses in repo in server-sent-event fashion", response = SseEmitter.class)
    @GetMapping(path = "/statusUpdateSSE", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    SseEmitter statusUpdateSSE();
}
