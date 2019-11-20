package hse.algosim.executor.server.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.algosimExecutor.base-path:/api}")
public class ExecuteApiController implements ExecuteApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ExecuteApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
