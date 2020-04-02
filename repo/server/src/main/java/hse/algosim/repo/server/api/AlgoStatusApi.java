package hse.algosim.repo.server.api;

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
import java.util.UUID;

@Validated
@Api(value = "algoStatus", description = "the algoStatus API")
public interface AlgoStatusApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmStatus", notes = "Returns an algorithm status", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully deleted"),
        @ApiResponse(code = 404, message = "Status not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmStatus(@ApiParam(value = "UUID of algorithm which status will be deleted",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getAlgorithmStatus", notes = "Returns an algorithm status", response = SrcStatus.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully fetched", response = SrcStatus.class),
        @ApiResponse(code = 404, message = "Status not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<SrcStatus> getAlgorithmStatus(@ApiParam(value = "UUID of algorithm which status will be fetched",required=true) @PathVariable("id") UUID id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"errorTrace\" : \"errorTrace\", \"winloss\" : \"+200kk\", \"status\" : \"SCHEDULED FOR COMPILATION\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "replaceAlgorithmStatus", notes = "Replaces the algorithm status and/or benchmarks", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully replaced"),
        @ApiResponse(code = 404, message = "Source code / status not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> replaceAlgorithmStatus(@ApiParam(value = "UUID of algorithm which status will be replaced",required=true) @PathVariable("id") UUID id,@ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "uploadAlgorithmStatus", notes = "Uploads the algorithm status and/or benchmarks", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status successfully uploaded"),
        @ApiResponse(code = 404, message = "Source code not found for this UUID") })
    @RequestMapping(value = "/algoStatus/{id}",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> uploadAlgorithmStatus(@ApiParam(value = "UUID of algorithm which status will be uploaded",required=true) @PathVariable("id") UUID id,@ApiParam(value = "Status to be uploaded" ,required=true )  @Valid @RequestBody SrcStatus srcStatus) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
