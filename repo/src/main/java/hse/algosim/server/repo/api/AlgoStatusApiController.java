package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final NativeWebRequest request;

    private static final SseEmitter emitter = new SseEmitter(0l);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String getStatusSql = "select status, error_trace, winloss from statuses where algo_id=?;";
    private static final String insertStatusSql = "insert into statuses(algo_id, status, error_trace, winloss) values (?,?,?,?);";
    private static final String updateStatusSql = "update statuses set status=?, error_trace=?, winloss=? where algo_id=?;";
    private static final String deleteStatusSql = "delete from statuses where algo_id=?;";
    private static final String getTopStatusSql = "select algo_id from statuses where status='SUCCESSFULLY_EXECUTED' order by winloss desc limit 10;";

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoStatusApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        try {
            jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) -> null);
        } catch (EmptyResultDataAccessException e) {
            jdbcTemplate.update(insertStatusSql, id, srcStatus.getStatus().toString(),srcStatus.getErrorTrace(),srcStatus.getMetrics());
            SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event().name(srcStatus.getStatus().toString()).data(id);
            emitter.send(sseEventBuilder);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResourceAlreadyExistsException("Status already uploaded for this id");
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        try {
            SrcStatus status = jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) ->
                    SrcStatus.builder()
                            .status(SrcStatus.StatusEnum.valueOf(rs.getString("status")))
                            .metrics(rs.getString("winloss"))
                            .errorTrace(rs.getString("error_trace")))
                    .build();
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        try {
            jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) -> null);
            jdbcTemplate.update(updateStatusSql, srcStatus.getStatus().toString(),srcStatus.getErrorTrace(),srcStatus.getMetrics(), id);
            SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event().name(srcStatus.getStatus().toString()).data(id);
            emitter.send(sseEventBuilder);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") String id) {
        try {
            jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) ->
                    SrcStatus.builder()
                            .status(SrcStatus.StatusEnum.valueOf(rs.getString("status")))
                            .metrics(rs.getString("winloss"))
                            .errorTrace(rs.getString("error_trace")))
                    .build();
            jdbcTemplate.update(deleteStatusSql, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray res = new IdArray(jdbcTemplate.query(getTopStatusSql, (rs, rowNum) -> rs.getString("algo_id")));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/stream-sse-mvc", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public SseEmitter streamSseMvc() {
        return emitter;
    }
}
