package hse.algosim.repo.server.api;

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

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoJarApiController implements AlgoJarApi {

    private final NativeWebRequest request;
    private Map<String,String> ids;

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoJarApiController(NativeWebRequest request) {
        this.request = request;
        ids = new HashMap<>();
        new File("artifacts/").mkdirs();
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Resource> getAlgorithmJar(@PathVariable("id") UUID id) {
        System.out.println("Sending artifact "+id.toString());
        Resource r = new PathResource(ids.get(id.toString()));
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+id.toString());
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> uploadAlgorithmJar(@PathVariable("id") UUID id, @Valid MultipartFile jar) {
        System.out.println("Received artifact "+id.toString());
        System.out.println(id.toString());
        try {
            File receivedArtifact = new File("artifacts/"+id.toString()).getAbsoluteFile();
            ids.put(id.toString(),receivedArtifact.getAbsolutePath());
            jar.transferTo(receivedArtifact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}