package hse.algosim.server.executor.api;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Validated
@Api(value = "execute", description = "the execute API", tags={ "executor" })
public interface ExecuteApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "executeAlgorithm", notes = "Gets the artifact from nexus, executes and benchmarks it")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully executed"),
        @ApiResponse(code = 404, message = "Artifact not found for this UUID") })
    @RequestMapping(value = "/execute/{id}",
        method = RequestMethod.POST)
    default ResponseEntity<Void> executeAlgorithm(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
