package hse.algosim.executor.server.api;

import hse.algosim.repo.client.api.ApiClient;
import hse.algosim.repo.client.api.ApiException;
import hse.algosim.repo.client.api.RepoApiClientInstance;
import hse.algosim.repo.client.model.SrcStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("${openapi.algosimExecutor.base-path:/api}")
public class ExecuteApiController implements ExecuteApi {

    private final NativeWebRequest request;
    private final static RepoApiClientInstance repoApiClient = new RepoApiClientInstance(new ApiClient().setBasePath("http://localhost:8000/repo/api"));
    private final static ThreadPoolExecutor singleThreadExecutor = new ThreadPoolExecutor(
            1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2));

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
        try {
            singleThreadExecutor.submit(()->{
                SrcStatus srcStatus = new SrcStatus();
                File jar = null;
                try {
                    repoApiClient.replaceAlgorithmStatus(
                            id,
                            new SrcStatus().status(SrcStatus.StatusEnum.EXECUTING));
                    jar = repoApiClient.getAlgorithmJar(id);

                    Process p = new ProcessBuilder()
                            .command(Arrays.asList("java", "-jar", jar.getAbsolutePath()))
                            .start();
                    BufferedReader pErrorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    BufferedReader pOutputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    srcStatus = srcStatus
                            .status(SrcStatus.StatusEnum.SUCCESSFULLY_EXECUTED)
                            .errorTrace(pErrorReader.lines().collect(Collectors.joining(System.lineSeparator())))
                            .winloss(pOutputReader.lines().collect(Collectors.joining(System.lineSeparator())));

                    if (p.waitFor() != 0) {
                        srcStatus.setStatus(SrcStatus.StatusEnum.EXECUTION_FAILED);
                    }
                } catch (InterruptedException | ApiException | IOException e ){
                    StringWriter stringWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(stringWriter));
                    srcStatus = srcStatus
                            .status(SrcStatus.StatusEnum.EXECUTION_FAILED)
                            .errorTrace(stringWriter.toString());
                } finally {
                    if (jar != null){
                        jar.delete();
                    }
                    try {
                        repoApiClient.replaceAlgorithmStatus(
                                id,
                                srcStatus);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (RejectedExecutionException re){
            System.out.println("Caught RejectedExecutionException" + id.toString());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
