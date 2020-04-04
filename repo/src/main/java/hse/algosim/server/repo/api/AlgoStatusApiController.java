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
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final NativeWebRequest request;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static Map<String,SrcStatus> ids = new HashMap<>();
    private static Map<Double, Set<String>> top = new ConcurrentSkipListMap<>(Collections.reverseOrder());

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
        if(srcStatus.getWinloss()!=null) {
            try {
                JsonNode root = mapper.readTree(srcStatus.getWinloss());
                Double winloss = root.get("winloss").asDouble();
                Set<String> newId = new HashSet<>();
                newId.add(id.toString());
                top.merge(winloss, newId,(set1,set2)->{set1.addAll(set2);return set1;});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        SrcStatus prevStatus = ids.get(id.toString());
        if (prevStatus == null)
            throw new ResourceNotFoundException("Status not found for this UUID");
        ids.replace(id.toString(),srcStatus);
        try {
            if(prevStatus.getWinloss() != null) {
                JsonNode prevWinLossNode = mapper.readTree(prevStatus.getWinloss()).get("winloss");
                if (prevWinLossNode != null) {
                    top.compute(prevWinLossNode.asDouble(),(key,idSet)->{idSet.remove(id.toString());return idSet;});
                }
            }
            if (srcStatus.getWinloss() != null) {
                JsonNode winLossNode = mapper.readTree(srcStatus.getWinloss()).get("winloss");
                if (winLossNode != null) {
                    Set<String> newId = new HashSet<>();
                    newId.add(id.toString());
                    top.merge(winLossNode.asDouble(), newId,(set1,set2)->{set1.addAll(set2);return set1;});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") UUID id) {
        SrcStatus prevStatus = ids.get(id.toString());
        if (prevStatus == null)
            throw new ResourceNotFoundException("Status not found for this UUID");
        ids.remove(id.toString());
        if (prevStatus.getWinloss() != null) {
            try {
                JsonNode prevWinLossNode = mapper.readTree(prevStatus.getWinloss()).get("winloss");
                if (prevWinLossNode != null) {
                    top.compute(prevWinLossNode.asDouble(),(key,idSet)->{idSet.remove(id.toString());return idSet;});
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray res = new IdArray().id(top.values()
                .stream()
                .filter(set -> !set.isEmpty())
                .flatMap(Collection::stream)
                .limit(10)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
