package hse.algosim.server.compiler.api;

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
@Api(value = "Compiler", description = "the Compiler API")
public interface CompilerApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /compile/{id}
     * Gets the sources from the db and compiles them
     *
     * @param id UUID of algorithm to fetch (required)
     * @return Successfully compiled (status code 200)
     *         or Source code not found for this UUID (status code 404)
     */
    @ApiOperation(value = "", nickname = "compileAlgorithm", notes = "Gets the sources from the db and compiles them", tags={ "compiler", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully compiled"),
        @ApiResponse(code = 404, message = "Source code not found for this UUID") })
    @RequestMapping(value = "/compile/{id}",
        method = RequestMethod.POST)
    default ResponseEntity<Void> compileAlgorithm(@ApiParam(value = "UUID of algorithm to fetch",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /ready
     * Returns readiness of the host for compilation
     *
     * @return Ready (status code 200)
     *         or Busy (status code 503)
     */
    @ApiOperation(value = "", nickname = "getReadiness", notes = "Returns readiness of the host for compilation", tags={ "compiler", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ready"),
        @ApiResponse(code = 503, message = "Busy") })
    @RequestMapping(value = "/ready",
        method = RequestMethod.GET)
    default ResponseEntity<Void> getReadiness() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
