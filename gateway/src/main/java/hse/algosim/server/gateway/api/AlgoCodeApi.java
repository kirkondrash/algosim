package hse.algosim.server.gateway.api;

import hse.algosim.server.model.RecommendationModel;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.UserCodeInfo;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@Api(value = "algoCode", description = "the algoCode API", tags={ "gateway" })
public interface AlgoCodeApi {

    @ApiOperation(value = "", nickname = "codeBenchmark", notes = "Gets code loaded by user, assigns the UUID to it. The code will later be compiled and executed", response = Map.class, authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Source code was uploaded successfully", response = UserCodeInfo.class) })
    @RequestMapping(value = "/algoCode",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    default ResponseEntity<UserCodeInfo> codeBenchmark(
            @ApiParam(value = "code", required=true) @Valid @RequestPart("code") MultipartFile code,
            @ApiParam(value = "user id", required=true) @RequestPart(value="userId")  String userId,
            @ApiParam(value = "user's name of algorithm", required=true) @RequestPart(value="userAlgoName")  String userAlgoName,
            @ApiParam(value = "recommendation models used") @RequestPart(value = "models", required = false) String commaSeparatedModels
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getTop", notes = "Gets top 10 algos ids and winloss scores", response = Map.class, authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class) })
    @RequestMapping(value = "/getTop",
            produces = { "application/json" },
            method = RequestMethod.GET)
    default ResponseEntity<List<Map<String,Object>>> getTop() {
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

    @ApiOperation(value = "", nickname = "getRecommendation", notes = "Returns a redirect to a given model", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "URL redirect", response = String.class),
            @ApiResponse(code = 404, message = "Model not found for this id") })
    @RequestMapping(value = "/recommendation",
            produces = { MediaType.TEXT_PLAIN_VALUE },
            method = RequestMethod.GET)
    default ResponseEntity<String> getRecommendation(
            @ApiParam(value = "id of recommendation model which url will be returned",required=true) @RequestParam("modelName") String modelName,
            @ApiParam(value = "id of algo for which uthe model is fetched",required=true) @RequestParam("algoId") String algoId
            ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "", nickname = "registerRecommendation", notes = "Uploads a recommendation model's config file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Model was registered successfully")
    })
    @RequestMapping(value = "/recommendation",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    default ResponseEntity<Void> registerRecommendation(@RequestBody RecommendationModel model) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
