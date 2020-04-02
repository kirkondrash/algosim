package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final NativeWebRequest request;
    private static Map<String,SrcStatus> ids = new HashMap<>();

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoStatusApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") UUID id, @Valid @RequestBody SrcStatus srcStatus) {
        if (ids.containsKey(id.toString()))
            throw new ResourceAlreadyExistsException("Status already uploaded for this UUID");
        ids.put(id.toString(),srcStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") UUID id) {
        SrcStatus status = ids.get(id.toString());
        if (status == null)
            throw new ResourceNotFoundException("Status not found for this UUID");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") UUID id, @Valid @RequestBody SrcStatus srcStatus) {
        SrcStatus status = ids.get(id.toString());
        if (status == null)
            throw new ResourceNotFoundException("Status not found for this UUID");
        ids.replace(id.toString(),srcStatus);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") UUID id) {
        SrcStatus status = ids.get(id.toString());
        if (status == null)
            throw new ResourceNotFoundException("Status not found for this UUID");
        ids.remove(id.toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
