package hse.algosim.server.repo.api;

import hse.algosim.server.model.SrcMeta;
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
@Api(value = "algoMeta", description = "the algoMeta API")
public interface AlgoMetaApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "deleteAlgorithmMeta", notes = "Deletes algorithm metadata based on the UUID supplied", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Metadata successfully deleted"),
        @ApiResponse(code = 404, message = "Metadata not found for this UUID") })
    @RequestMapping(value = "/algoMeta/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteAlgorithmMeta(@ApiParam(value = "UUID of algorithm which metadata will be deleted",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getAlgorithmMeta", notes = "Returns algorithm metadata", response = SrcMeta.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Metadata successfully fetched", response = SrcMeta.class),
        @ApiResponse(code = 404, message = "Metadata not found for this UUID") })
    @RequestMapping(value = "/algoMeta/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<SrcMeta> getAlgorithmMeta(@ApiParam(value = "UUID of algorithm which metadata is fetched",required=true) @PathVariable("id") UUID id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"author\" : \"John Doe\", \"description\" : \"Based on Stealth\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "replaceAlgorithmMeta", notes = "Replaces algorithm metadata like description or author", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Metadata successfully replaced"),
        @ApiResponse(code = 404, message = "Source code / metadata not found for this UUID") })
    @RequestMapping(value = "/algoMeta/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> replaceAlgorithmMeta(@ApiParam(value = "UUID of algorithm which metadata is replaced",required=true) @PathVariable("id") UUID id,@ApiParam(value = "Metadata to be uploaded" ,required=true )  @Valid @RequestBody SrcMeta srcMeta) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "uploadAlgorithmMeta", notes = "Upoads algorithm metadata like description or author", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Metadata successfully uploaded"),
        @ApiResponse(code = 404, message = "Source not found for this UUID") })
    @RequestMapping(value = "/algoMeta/{id}",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> uploadAlgorithmMeta(@ApiParam(value = "UUID of algorithm which metadata is uploaded",required=true) @PathVariable("id") UUID id,@ApiParam(value = "Metadata to be uploaded" ,required=true )  @Valid @RequestBody SrcMeta srcMeta) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
