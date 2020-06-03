package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.SrcMeta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoMetaApiController implements AlgoMetaApi {

    private final NativeWebRequest request;
    private static final Map<String, SrcMeta> algorithmMeta = new HashMap<>();;

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoMetaApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmMeta(@PathVariable("id") String id, @RequestBody @Valid SrcMeta srcMeta) {
        synchronized (algorithmMeta) {
            if (algorithmMeta.containsKey(id))
                throw new ResourceAlreadyExistsException("Metadata already uploaded for this id");
            algorithmMeta.put(id, srcMeta);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcMeta> readAlgorithmMeta(@PathVariable("id") String id) {
        SrcMeta meta = algorithmMeta.get(id);
        if (meta == null)
            throw new ResourceNotFoundException("Metadata not found for this id");
        return new ResponseEntity<>(meta, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmMeta(@PathVariable("id") String id, @Valid SrcMeta srcMeta) {
        synchronized (algorithmMeta) {
            SrcMeta meta = algorithmMeta.get(id);
            if (meta == null)
                throw new ResourceNotFoundException("Metadata not found for this id");
            algorithmMeta.replace(id, srcMeta);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmMeta(@PathVariable("id") String id) {
        synchronized (algorithmMeta) {
            SrcMeta meta = algorithmMeta.get(id);
            if (meta == null)
                throw new ResourceNotFoundException("Metadata not found for this id");
            algorithmMeta.remove(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
