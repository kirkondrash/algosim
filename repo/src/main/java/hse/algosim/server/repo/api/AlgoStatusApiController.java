package hse.algosim.server.repo.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final NativeWebRequest request;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Map<String,SrcStatus> algorithmStatuses = new HashMap<>();
    private static Map<BigDecimal, Set<String>> topAlgorithms = new ConcurrentSkipListMap<>((val, val2) -> val.compareTo(val2)*-1);

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoStatusApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        synchronized (algorithmStatuses) {
            if (algorithmStatuses.containsKey(id))
                throw new ResourceAlreadyExistsException("Status already uploaded for this id");
            algorithmStatuses.put(id, srcStatus);
        }
        if(srcStatus.getMetrics()!=null) {
            try {
                JsonNode root = mapper.readTree(srcStatus.getMetrics());
                String winloss = root.get("winloss").asText();
                Set<String> newId = new HashSet<>();
                newId.add(id);
                topAlgorithms.merge(new BigDecimal(winloss), newId,(set1, set2)->{set1.addAll(set2);return set1;});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        SrcStatus status = algorithmStatuses.get(id);
        if (status == null)
            throw new ResourceNotFoundException("Status not found for this id");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        SrcStatus prevStatus;
        synchronized (algorithmStatuses) {
            prevStatus = algorithmStatuses.get(id);
            if (prevStatus == null)
                throw new ResourceNotFoundException("Status not found for this id");
            algorithmStatuses.replace(id, srcStatus);
        }
        try {
            if(prevStatus.getMetrics() != null) {
                JsonNode prevWinLossNode = mapper.readTree(prevStatus.getMetrics()).get("winloss");
                if (prevWinLossNode != null) {
                    topAlgorithms.compute(new BigDecimal(prevWinLossNode.asText()),(key, idSet)->{idSet.remove(id);return idSet;});
                }
            }
            if (srcStatus.getMetrics() != null) {
                JsonNode winLossNode = mapper.readTree(srcStatus.getMetrics()).get("winloss");
                if (winLossNode != null) {
                    Set<String> newId = new HashSet<>();
                    newId.add(id);
                    topAlgorithms.merge(new BigDecimal(winLossNode.asText()), newId,(set1, set2)->{set1.addAll(set2);return set1;});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") String id) {
        SrcStatus prevStatus;
        synchronized (algorithmStatuses) {
            prevStatus = algorithmStatuses.get(id);
            if (prevStatus == null)
                throw new ResourceNotFoundException("Status not found for this id");
            algorithmStatuses.remove(id);
        }
        if (prevStatus.getMetrics() != null) {
            try {
                JsonNode prevWinLossNode = mapper.readTree(prevStatus.getMetrics()).get("winloss");
                if (prevWinLossNode != null) {
                    topAlgorithms.compute(new BigDecimal(prevWinLossNode.asText()),(key, idSet)->{idSet.remove(id);return idSet;});
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray res = new IdArray().id(topAlgorithms.values()
                .stream()
                .filter(set -> !set.isEmpty())
                .flatMap(Collection::stream)
                .limit(10)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
