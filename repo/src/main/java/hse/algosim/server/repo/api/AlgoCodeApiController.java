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
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private static final Map<String,File> codeFiles = new HashMap<>();

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoCodeApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmCode(@PathVariable("id") String id, @Valid MultipartFile code) {
        synchronized (codeFiles) {
            if (codeFiles.containsKey(id))
                throw new ResourceAlreadyExistsException("Source code already uploaded for this id");
            saveFile(id, code);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmCode(@PathVariable("id") String id) {
        File code = getFile(id);
        Resource r = new PathResource(code.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+ id);
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmCode(@PathVariable("id") String id, @Valid MultipartFile code) {
        synchronized (codeFiles) {
            File oldCode = getFile(id);
            oldCode.delete();
            saveFile(id, code);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmCode(@PathVariable("id") String id) {
        synchronized (codeFiles) {
            File code = getFile(id);
            code.delete();
            codeFiles.remove(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public File getFile(String id){
        File code = codeFiles.get(id);
        if (code == null)
            throw new ResourceNotFoundException("Source code not found for this id");
        return code;
    }

    public void saveFile(String id, MultipartFile code){
        try {
            File receivedFile = new File("files/"+ id).getAbsoluteFile();
            receivedFile.getParentFile().mkdirs();
            codeFiles.put(id,receivedFile);
            code.transferTo(receivedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
