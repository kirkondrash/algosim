package hse.algosim.repo.server.api;

import hse.algosim.server.model.SrcStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final NativeWebRequest request;
    private Map<UUID,SrcStatus> srcStatuses = new HashMap<>();

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoStatusApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<SrcStatus> getAlgorithmStatus(@PathVariable("id") UUID id) {
        if (!srcStatuses.containsKey(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(srcStatuses.get(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> replaceAlgorithmStatus(@PathVariable("id") UUID id, @Valid @RequestBody SrcStatus srcStatus) {
        if (!srcStatuses.containsKey(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        srcStatuses.put(id,srcStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> uploadAlgorithmStatus(@PathVariable("id") UUID id, @Valid @RequestBody SrcStatus srcStatus) {
        if (srcStatuses.containsKey(id))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        srcStatuses.put(id,srcStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
