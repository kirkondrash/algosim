package hse.algosim.repo.server.api;

import hse.algosim.repo.server.model.IdArray;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private Map<String,String> ids;

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoCodeApiController(NativeWebRequest request) {
        this.request = request;
        ids = new HashMap<>();
        new File("files/").mkdirs();
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Resource> getAlgorithmCode(@PathVariable("id") UUID id) {
        System.out.println("Sending file!");
        Resource r = new PathResource(ids.get(id.toString()));
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+id.toString());
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> uploadAlgorithmCode(@PathVariable("id") UUID id, @Valid MultipartFile code) {
        System.out.println("Received file!");
        System.out.println(id.toString());
        try {
            File receivedFile = new File("files/"+id.toString()).getAbsoluteFile();
            ids.put(id.toString(),receivedFile.getAbsolutePath());
            code.transferTo(receivedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray res = new IdArray().id(
                ids.keySet()
                        .stream()
                        .limit(10)
                        .collect(Collectors.toList()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
