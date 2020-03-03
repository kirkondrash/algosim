package hse.algosim.gateway.server.api;

import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.DefaultApi;
import hse.algosim.repo.client.model.IdArray;
import hse.algosim.repo.client.model.SrcMeta;
import hse.algosim.repo.client.model.SrcStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.algosimGateway.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoCodeApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Map<String,String>> getAlgorithmCode(@Valid MultipartFile code) {
        ApiClient defaultClient = new ApiClient().setBasePath("http://localhost:8000/repo/api");
        DefaultApi apiInstance = new DefaultApi(defaultClient);
        UUID id = UUID.randomUUID(); // UUID | UUID of algorithm to fetch
        SrcMeta srcMeta = new SrcMeta(); // SrcStatus | Status to be uploaded
        try {
            File f = new File(id.toString()).getAbsoluteFile();
            code.transferTo(f);
            System.out.println("uploading file");
            apiInstance.uploadAlgorithmCode(id,f);
//            System.out.println("uploading meta");
//            apiInstance.uploadAlgorithmMeta(id,new SrcMeta().author("mark").description("hi"));
//            System.out.println("getting file");
//            File receivedFile = apiInstance.findAlgorithmCode(id);
//            System.out.println(new String(Files.readAllBytes(receivedFile.toPath())));
//            System.out.println("getting meta");
//            SrcMeta receivedMeta = apiInstance.findAlgorithmMeta(id);
//            System.out.println(receivedMeta.getAuthor()+receivedMeta.getDescription());
            f.delete();
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#changeAlgorithmStatus");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> res = new HashMap<>();
        res.put("id",id.toString());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String,String>> getTop(){
        Map<String,String> res = new HashMap<>();
        ApiClient defaultClient = new ApiClient().setBasePath("http://localhost:8000/repo/api");
        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            IdArray ids = apiInstance.getTopCode();
            ids.getId().forEach( id -> {
                try {
                    File receivedFile = apiInstance.findAlgorithmCode(UUID.fromString(id));
                    String firstLine = Files.readAllLines(receivedFile.toPath()).get(0);
                    System.out.println(firstLine);
                    res.put(id,firstLine);
                } catch (ApiException | IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
