package hse.algosim.server.repo.api;

import hse.algosim.server.model.SrcMeta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoMetaApiController implements AlgoMetaApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoMetaApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<SrcMeta> getAlgorithmMeta(@PathVariable("id") UUID id) {
        System.out.println("Sending meta!");
        return new ResponseEntity<>(new SrcMeta().author("hello").description("there"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> uploadAlgorithmMeta(@PathVariable("id") UUID id, @RequestBody @Valid SrcMeta srcMeta) {
        System.out.println("Received meta!");
        System.out.println(srcMeta.getDescription()+srcMeta.getAuthor());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
