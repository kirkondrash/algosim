package hse.algosim.server.repo.api;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.algosimRepo.base-path:/api}")
public class AlgoCodeApiController implements AlgoCodeApi {

    private final NativeWebRequest request;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String getPathSql = "select path from sources where algo_id=?;";
    private static final String insertPathSql = "insert into sources(algo_id, path) values (?,?);";
    private static final String deletePathSql = "delete from sources where algo_id=?;";

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoCodeApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmCode(@PathVariable("id") String id, @Valid MultipartFile code) {
        try {
            jdbcTemplate.queryForObject(getPathSql, new Object[]{id}, (rs, rowNum) -> rs.getString("path"));
        } catch (EmptyResultDataAccessException e) {
            saveFile(id, code);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResourceAlreadyExistsException("Source code already uploaded for this id");
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmCode(@PathVariable("id") String id) {
        File code = getFile(id);
        Resource r = new PathResource(code.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+ id);
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmCode(@PathVariable("id") String id, @Valid MultipartFile code) {
        File oldCode = getFile(id);
        oldCode.delete();
        jdbcTemplate.update(deletePathSql, id);
        saveFile(id, code);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmCode(@PathVariable("id") String id) {
        File code = getFile(id);
        code.delete();
        jdbcTemplate.update(deletePathSql, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public File getFile(String id){
        try {
            String codePath = jdbcTemplate.queryForObject(getPathSql, new Object[]{id}, (rs, rowNum) -> rs.getString("path"));
            return new File(codePath);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Source code not found for this id");
        }
    }

    public void saveFile(String id, MultipartFile code){
        try {
            File receivedFile = new File("files/"+ id).getAbsoluteFile();
            receivedFile.getParentFile().mkdirs();
            code.transferTo(receivedFile);
            jdbcTemplate.update(insertPathSql, id, receivedFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
