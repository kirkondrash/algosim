package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
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

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoJarApiController implements AlgoJarApi {

    private final NativeWebRequest request;
    private static final Map<String,File> artifactFiles = new HashMap<>();

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoJarApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmJar(@PathVariable("id") String id, @Valid MultipartFile jar) {
        synchronized (artifactFiles) {
            if (artifactFiles.containsKey(id))
                throw new ResourceAlreadyExistsException("Artifact already uploaded for this id");
            saveFile(id, jar);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmJar(@PathVariable("id") String id) {
        File jar = getFile(id);
        Resource r = new PathResource(jar.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+ id);
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmJar(@PathVariable("id") String id, @Valid MultipartFile jar) {
        synchronized (artifactFiles) {
            File oldJar = getFile(id);
            oldJar.delete();
            saveFile(id, jar);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmJar(@PathVariable("id") String id) {
        synchronized (artifactFiles) {
            File jar = getFile(id);
            jar.delete();
            artifactFiles.remove(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public File getFile(String id){
        File jar = artifactFiles.get(id);
        if (jar == null)
            throw new ResourceNotFoundException("Artifact not found for this id");
        return jar;
    }

    public void saveFile(String id, MultipartFile jar){
        try {
            File receivedArtifact = new File("artifacts/"+ id).getAbsoluteFile();
            receivedArtifact.getParentFile().mkdirs();
            artifactFiles.put(id,receivedArtifact);
            jar.transferTo(receivedArtifact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}