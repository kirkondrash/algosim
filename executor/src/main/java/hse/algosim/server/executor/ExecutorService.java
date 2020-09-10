package hse.algosim.server.executor;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ExecutorService {
    private final ProcessBuilder pb = new ProcessBuilder();

    public void runExecution(RepoApiClient repoApiClient,
                                    String id,
                                    String pathToQuotes,
                                    String dbUser,
                                    String dbPassword,
                                    String dbURL){
        SrcStatus.SrcStatusBuilder srcStatus = SrcStatus.builder();
        File jar = null;
        try {
            repoApiClient.updateAlgorithmStatus(
                    id,
                    SrcStatus.builder().status(SrcStatus.StatusEnum.EXECUTING).build());
            jar = new File(id);
            byte[] code = repoApiClient.readAlgorithmJar(id).getBody();
            FileUtils.writeByteArrayToFile(jar, code);

            Process p = pb
                    .command(Arrays.asList(
                            "java",
                            String.format("-Dframework.algo_id=%s",id),
                            String.format("-DpathToQuotes=%s", pathToQuotes),
                            String.format("-Dpostgres.username=%s", dbUser),
                            String.format("-Dpostgres.password=%s", dbPassword),
                            String.format("-Dpostgres.url=%s", dbURL),
                            "-jar",
                            jar.getAbsolutePath()))
                    .start();
            try ( BufferedReader pErrorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                  BufferedReader pOutputReader = new BufferedReader(new InputStreamReader(p.getInputStream()))){

            srcStatus
                    .status(SrcStatus.StatusEnum.SUCCESSFULLY_EXECUTED)
                    .errorTrace(pErrorReader.lines().collect(Collectors.joining(System.lineSeparator())))
                    .metrics(pOutputReader.lines().collect(Collectors.joining(System.lineSeparator())));

                if (p.waitFor() != 0) {
                    srcStatus.status(SrcStatus.StatusEnum.EXECUTION_FAILED);
                }
            }
        } catch (InterruptedException | FeignException | IOException e ){
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            srcStatus
                    .status(SrcStatus.StatusEnum.EXECUTION_FAILED)
                    .errorTrace(stringWriter.toString());
        } finally {
            if (jar != null){
                jar.delete();
            }
            try {
                repoApiClient.updateAlgorithmStatus(
                        id,
                        srcStatus.build());
            } catch (FeignException e) {
                log.error("{}",e.getCause());
            }
        }
    }
}
