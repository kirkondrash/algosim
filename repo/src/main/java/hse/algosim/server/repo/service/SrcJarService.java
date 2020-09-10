package hse.algosim.server.repo.service;

import hse.algosim.server.exceptions.ResourceAlreadyExistsException;
import hse.algosim.server.exceptions.ResourceNotFoundException;
import hse.algosim.server.model.SrcJar;
import hse.algosim.server.repo.repository.SrcJarRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SrcJarService {

    private final SrcJarRepository srcJarRepository;

    @Autowired
    public SrcJarService(SrcJarRepository srcJarRepository) {
        this.srcJarRepository = srcJarRepository;
    }

    @SneakyThrows
    public void createAlgorithmJar(String id, MultipartFile jar) {
        srcJarRepository
                .findByAlgoUserId(id)
                .ifPresent(prevJarPath -> {throw new ResourceAlreadyExistsException(String.format("Artifact %s already exists for this id", prevJarPath.getPath(), prevJarPath.getAlgoUserId()));});
        saveFile(id, jar);
    }

    public File getAlgorithmJarFile(String id) {
        return new File(readAlgorithmJar(id).getPath());
    }

    private SrcJar readAlgorithmJar(String id) {
        SrcJar srcJar = srcJarRepository
                .findByAlgoUserId(id)
                .orElseThrow(()->{throw new ResourceNotFoundException(String.format("Artifact not found for id %s", id));});
        return srcJar;
    }

    @SneakyThrows
    public void updateAlgorithmStatus(String id, MultipartFile jar) {
        deleteAlgorithmJar(id);
        createAlgorithmJar(id, jar);
    }

    @SneakyThrows
    public void deleteAlgorithmJar(String id) {
        SrcJar srcJar = readAlgorithmJar(id);
        srcJarRepository.delete(srcJar);
        Files.delete(Paths.get(srcJar.getPath()));
    }

    @SneakyThrows
    private void saveFile(String id, MultipartFile jar){
        File receivedArtifact = new File("artifacts/"+ id).getAbsoluteFile();
        receivedArtifact.getParentFile().mkdirs();
        jar.transferTo(receivedArtifact);
        srcJarRepository.save(SrcJar.builder().algoUserId(id).path(receivedArtifact.getAbsolutePath()).build());
    }

}
