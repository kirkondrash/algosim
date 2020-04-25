package hse.algosim.server.repo.api;

import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@Api(value = "algoStatus", description = "the algoStatus API", tags={ "status" })
public interface AlgoStatusApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "createAlgorithmStatus", notes = "Uploads the algorithm status and/or benchmarks")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Status successfully uploaded"),
            @ApiResponse(code = 409, message = "Source code not found for this id")
    })
    @RequestMapping(value = "/algoStatus/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    default ResponseEntity<Void> createAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be uploaded",required=true) @PathVariable("id") String id, @ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "readAlgorithmStatus", notes = "Returns an algorithm status", response = SrcStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status successfully fetched", response = SrcStatus.class),
            @ApiResponse(code = 404, message = "Status not found for this id") })
    @RequestMapping(value = "/algoStatus/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    default ResponseEntity<SrcStatus> readAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be fetched",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "updateAlgorithmStatus", notes = "Replaces the algorithm status and/or benchmarks")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Status successfully replaced"),
            @ApiResponse(code = 404, message = "Source code / status not found for this id") })
    @RequestMapping(value = "/algoStatus/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.PUT)
    default ResponseEntity<Void> updateAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be replaced",required=true) @PathVariable("id") String id, @ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmStatus", notes = "Returns an algorithm status")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Status successfully deleted"),
            @ApiResponse(code = 404, message = "Status not found for this id") })
    @RequestMapping(value = "/algoStatus/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmStatus(@ApiParam(value = "id of algorithm which status will be deleted",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "getTopCode", notes = "Returns some 10 algos ids", response = IdArray.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Source code ids", response = IdArray.class) })
    @RequestMapping(value = "/getTopCode",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.GET)
    default ResponseEntity<IdArray> getTopCode() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
