package hse.algosim.server.repo.service;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.SrcCode;
import hse.algosim.server.repo.repository.SrcCodeRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class SrcCodeService {

    private final SrcCodeRepository srcCodeRepository;

    @Autowired
    public SrcCodeService(SrcCodeRepository srcCodeRepository) {
        this.srcCodeRepository = srcCodeRepository;
    }

    @SneakyThrows
    public void createAlgorithmCode(String id, MultipartFile code) {
        srcCodeRepository
                .findByAlgoUserId(id)
                .ifPresent(prevJarPath -> {throw new ResourceAlreadyExistsException(String.format("Source %s already exists for this id", prevJarPath.getPath(), prevJarPath.getAlgoUserId()));});
        saveFile(id, code);
//        log.info("Uploaded source for {} to the database",id);
    }

    public File getAlgorithmCodeFile(String id) {
//        log.info("Source for {} is requested from the database",id);
        return new File(readAlgorithmCode(id).getPath());
    }

    private SrcCode readAlgorithmCode(String id) {
        SrcCode srcCode = srcCodeRepository
                .findByAlgoUserId(id)
                .orElseThrow(()->{throw new ResourceNotFoundException(String.format("Sources not found for id %s", id));});
        return srcCode;
    }

    @SneakyThrows
    public void updateAlgorithmStatus(String id, MultipartFile code) {
        deleteAlgorithmCode(id);
        createAlgorithmCode(id, code);
    }

    @SneakyThrows
    public void deleteAlgorithmCode(String id) {
        SrcCode srcCode = readAlgorithmCode(id);
        srcCodeRepository.delete(srcCode);
        Files.delete(Paths.get(srcCode.getPath()));
//        log.info("Source for {} was deleted from the database",id);
    }

    @SneakyThrows
    private void saveFile(String id, MultipartFile code){
        File receivedArtifact = new File("sources/"+ id).getAbsoluteFile();
        receivedArtifact.getParentFile().mkdirs();
        code.transferTo(receivedArtifact);
        srcCodeRepository.save(SrcCode.builder().algoUserId(id).path(receivedArtifact.getAbsolutePath()).build());
    }

}
