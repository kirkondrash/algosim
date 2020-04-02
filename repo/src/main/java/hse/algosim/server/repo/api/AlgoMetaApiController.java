package hse.algosim.server.repo.api;

import hse.algosim.server.model.SrcMeta;
import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
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
import java.util.UUID;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoMetaApiController implements AlgoMetaApi {

    private final NativeWebRequest request;
    private static Map<String, SrcMeta> ids = new HashMap<>();;

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoMetaApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmMeta(@PathVariable("id") UUID id, @RequestBody @Valid SrcMeta srcMeta) {
        if (ids.containsKey(id.toString()))
            throw new ResourceAlreadyExistsException("Metadata already uploaded for this UUID");
        ids.put(id.toString(),srcMeta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcMeta> readAlgorithmMeta(@PathVariable("id") UUID id) {
        SrcMeta meta = ids.get(id.toString());
        if (meta == null)
            throw new ResourceNotFoundException("Metadata not found for this UUID");
        return new ResponseEntity<>(meta, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmMeta(@PathVariable("id") UUID id, @Valid SrcMeta srcMeta) {
        SrcMeta meta = ids.get(id.toString());
        if (meta == null)
            throw new ResourceNotFoundException("Metadata not found for this UUID");
        ids.replace(id.toString(),srcMeta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmMeta(@PathVariable("id") UUID id) {
        SrcMeta meta = ids.get(id.toString());
        if (meta == null)
            throw new ResourceNotFoundException("Metadata not found for this UUID");
        ids.remove(id.toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
