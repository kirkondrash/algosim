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
import java.util.UUID;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;
    private static Map<String,File> ids = new HashMap<>();

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoCodeApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmCode(@PathVariable("id") UUID id, @Valid MultipartFile code) {
        if (ids.containsKey(id.toString()))
            throw new ResourceAlreadyExistsException("Source code already uploaded for this UUID");
        saveFile(id.toString(),code);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmCode(@PathVariable("id") UUID id) {
        File code = getFile(id.toString());
        Resource r = new PathResource(code.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+id.toString());
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmCode(@PathVariable("id") UUID id, @Valid MultipartFile code) {
        File oldCode = getFile(id.toString());
        oldCode.delete();
        saveFile(id.toString(),code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmCode(@PathVariable("id") UUID id) {
        File code = getFile(id.toString());
        code.delete();
        ids.remove(id.toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public File getFile(String id){
        File code = ids.get(id);
        if (code == null)
            throw new ResourceNotFoundException("Source code not found for this UUID");
        return code;
    }

    public void saveFile(String id, MultipartFile code){
        try {
            File receivedFile = new File("files/"+ id).getAbsoluteFile();
            receivedFile.getParentFile().mkdirs();
            ids.put(id,receivedFile);
            code.transferTo(receivedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
