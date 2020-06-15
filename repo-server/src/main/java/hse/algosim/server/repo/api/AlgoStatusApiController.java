package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.IdArray;
import hse.algosim.server.model.SrcStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoStatusApiController implements AlgoStatusApi {

    private final NativeWebRequest request;

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

    @Override
    public ResponseEntity<Void> createAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        try {
            jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) ->
                    new SrcStatus().status(SrcStatus.StatusEnum.valueOf(rs.getString("status"))).metrics(rs.getString("metrics")).errorTrace(rs.getString("error_trace")));
        } catch (EmptyResultDataAccessException e) {
            jdbcTemplate.update(insertStatusSql, id, srcStatus.getStatus().toString(),srcStatus.getErrorTrace(),srcStatus.getMetrics());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResourceAlreadyExistsException("Status already uploaded for this id");
    }

    @Override
    public ResponseEntity<SrcStatus> readAlgorithmStatus(@PathVariable("id") String id) {
        try {
            SrcStatus status = jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) ->
                    new SrcStatus().status(SrcStatus.StatusEnum.valueOf(rs.getString("status"))).metrics(rs.getString("winloss")).errorTrace(rs.getString("error_trace")));
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmStatus(@PathVariable("id") String id, @Valid @RequestBody SrcStatus srcStatus) {
        try {
            jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) ->
                    new SrcStatus().status(SrcStatus.StatusEnum.valueOf(rs.getString("status"))).metrics(rs.getString("winloss")).errorTrace(rs.getString("error_trace")));
            jdbcTemplate.update(updateStatusSql, srcStatus.getStatus().toString(),srcStatus.getErrorTrace(),srcStatus.getMetrics(), id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmStatus(@PathVariable("id") String id) {
        try {
            jdbcTemplate.queryForObject(getStatusSql, new Object[]{id}, (rs, rowNum) ->
                    new SrcStatus().status(SrcStatus.StatusEnum.valueOf(rs.getString("status"))).metrics(rs.getString("winloss")).errorTrace(rs.getString("error_trace")));
            jdbcTemplate.update(deleteStatusSql, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Status not found for this id");
        }
    }

    @Override
    public ResponseEntity<IdArray> getTopCode() {
        IdArray res = new IdArray().id(jdbcTemplate.query(getTopStatusSql, (rs, rowNum) -> rs.getString("algo_id")));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
