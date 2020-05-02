package hse.algosim.server.gateway.api;

import hse.algosim.server.model.UserCodeInfo;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "algoCode", description = "the algoCode API", tags={ "gateway" })
public interface AlgoCodeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "codeBenchmark", notes = "Gets code loaded by user, assigns the UUID to it. The code will later be compiled and executed", response = Map.class, authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Source code was uploaded successfully", response = UserCodeInfo.class) })
    @RequestMapping(value = "/algoCode",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    default ResponseEntity<UserCodeInfo> codeBenchmark(
            @ApiParam(value = "code") @Valid @RequestPart("code") MultipartFile code,
            @ApiParam(value = "user id", required=true) @RequestParam(value="userId", required=true)  String userId,
            @ApiParam(value = "user's name of algorithm", required=true) @RequestParam(value="userAlgoName", required=true)  String userAlgoName
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getTop", notes = "Gets some 10 algos ids and first line of files", response = Map.class, authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class) })
    @RequestMapping(value = "/getTop",
            produces = { "application/json" },
            method = RequestMethod.GET)
    default ResponseEntity<List<Map<String,Object>>> getTop() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
