package hse.algosim.server.compiler.api;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@Api(value = "Compiler", description = "the Compiler API", tags={ "compiler"})
public interface CompilerApi {

    /**
     * POST /compile/{id}
     * Gets the sources from the db and compiles them
     *
     * @param id id of algorithm to fetch (required)
     * @return Successfully compiled (status code 200)
     *         or Source code not found for this id (status code 404)
     */
    @ApiOperation(value = "", nickname = "compileAlgorithm", notes = "Gets the sources from the db and compiles them")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully compiled"),
        @ApiResponse(code = 404, message = "Source code not found for this id") })
    @RequestMapping(value = "/compile/{id}",
        method = RequestMethod.POST)
    default ResponseEntity<Void> compileAlgorithm(@ApiParam(value = "id of algorithm to fetch",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /ready
     * Returns readiness of the host for compilation
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
