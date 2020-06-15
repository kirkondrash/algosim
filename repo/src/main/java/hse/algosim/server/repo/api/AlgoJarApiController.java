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
public class AlgoJarApiController implements AlgoJarApi {

    private final NativeWebRequest request;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String getArtifactSql = "select path from artifacts where algo_id=?;";
    private static final String insertArtifactSql = "insert into artifacts(algo_id, path) values (?,?);";
    private static final String deleteArtifactSql = "delete from artifacts where algo_id=?;";

    @org.springframework.beans.factory.annotation.Autowired
    public AlgoJarApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createAlgorithmJar(@PathVariable("id") String id, @Valid MultipartFile jar) {
        try {
            jdbcTemplate.queryForObject(getArtifactSql, new Object[]{id}, (rs, rowNum) -> rs.getString("path"));
        } catch (EmptyResultDataAccessException e) {
            saveFile(id, jar);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResourceAlreadyExistsException("Artifact already uploaded for this id");
    }

    @Override
    public ResponseEntity<Resource> readAlgorithmJar(@PathVariable("id") String id) {
        File jar = getFile(id);
        Resource r = new PathResource(jar.toPath());
        HttpHeaders hp = new HttpHeaders();
        hp.add("Content-Disposition","attachment; filename="+ id);
        hp.add("Content-Type","application/octet-stream");
        return new ResponseEntity<>(r, hp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAlgorithmJar(@PathVariable("id") String id, @Valid MultipartFile jar) {
        File oldJar = getFile(id);
        oldJar.delete();
        jdbcTemplate.update(deleteArtifactSql, id);
        saveFile(id, jar);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAlgorithmJar(@PathVariable("id") String id) {
        File jar = getFile(id);
        jar.delete();
        jdbcTemplate.update(deleteArtifactSql, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public File getFile(String id){
        try {
            String jarPath = jdbcTemplate.queryForObject(getArtifactSql, new Object[]{id}, (rs, rowNum) -> rs.getString("path"));
            return new File(jarPath);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Artifact not found for this id");
        }
    }

    public void saveFile(String id, MultipartFile jar){
        try {
            File receivedArtifact = new File("artifacts/"+ id).getAbsoluteFile();
            receivedArtifact.getParentFile().mkdirs();
            jar.transferTo(receivedArtifact);
            jdbcTemplate.update(insertArtifactSql, id, receivedArtifact.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}