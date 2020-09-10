package hse.algosim.server.executor.api;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@Api(value = "execute", description = "the execute API", tags={ "executor" })
public interface ExecuteApi {

    @ApiOperation(value = "", nickname = "executeAlgorithm", notes = "Gets the artifact from nexus, executes and benchmarks it")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully executed"),
        @ApiResponse(code = 404, message = "Artifact not found for this id") })
    @RequestMapping(value = "/execute/{id}",
        method = RequestMethod.POST)
    default ResponseEntity<Void> executeAlgorithm(@ApiParam(value = "id of algorithm to fetch",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /ready
     * Returns readiness of the host for execution
     *
     * @return Ready (status code 200)
     *         or Busy (status code 503)
     */
    @ApiOperation(value = "", nickname = "getReadiness", notes = "Returns readiness of the host for compilation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ready"),
            @ApiResponse(code = 503, message = "Busy") })
    @RequestMapping(value = "/ready",
            method = RequestMethod.GET)
    default ResponseEntity<Void> getReadiness() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
