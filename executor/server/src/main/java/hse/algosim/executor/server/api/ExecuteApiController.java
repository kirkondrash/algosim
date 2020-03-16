package hse.algosim.executor.server.api;

import com.google.common.collect.Lists;
import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.RepoApiClientInstance;
import hse.algosim.repo.client.model.SrcStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
@RequestMapping("${openapi.algosimExecutor.base-path:/api}")
public class ExecuteApiController implements ExecuteApi {

    private final NativeWebRequest request;
    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/repo/api"));

    @org.springframework.beans.factory.annotation.Autowired
    public ExecuteApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> executeAlgorithm(@PathVariable("id") UUID id) {
        File jar;
        try {
            repoApiClient.replaceAlgorithmStatus(
                    id,
                    new SrcStatus().status(SrcStatus.StatusEnum.EXECUTING));
            jar = repoApiClient.getAlgorithmJar(id);
        } catch (ApiException e) {
            e.printStackTrace();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.putAll(e.getResponseHeaders());
            return new ResponseEntity<>(
                    httpHeaders,
                    HttpStatus.valueOf(e.getCode()));
        }

        CompletableFuture<Void> execution = CompletableFuture.runAsync(()-> {
            try {
                SrcStatus srcStatus = new SrcStatus().status(SrcStatus.StatusEnum.SUCCESSFULLY_EXECUTED);
                ProcessBuilder processBuilder = new ProcessBuilder().command(Arrays.asList("java", "-jar", jar.getAbsolutePath()));
                Process p = processBuilder.start();
                int i = p.waitFor();
                if (i != 0) {
                    srcStatus.setStatus(SrcStatus.StatusEnum.EXECUTION_FAILED);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    srcStatus.setErrorTrace(reader.lines().collect(Collectors.joining(System.lineSeparator())));
                }
                else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    srcStatus.setWinloss(reader.lines().collect(Collectors.joining(System.lineSeparator())));
                }
                repoApiClient.replaceAlgorithmStatus(
                        id,
                        srcStatus);
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
