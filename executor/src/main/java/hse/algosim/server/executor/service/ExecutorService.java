package hse.algosim.server.executor.service;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExecutorService {
    private final ProcessBuilder pb = new ProcessBuilder();

    private final RepoApiClient repoApiClient;
    private final String frameworkQuotesPath;
    private final String dataSourceUser;
    private final String dataSourcePassword;
    private final String dataSourceUrl;

    @Autowired
    public ExecutorService(
            RepoApiClient repoApiClient,
            @Value("${framework.quotes.path}") String frameworkQuotesPath,
            @Value("${spring.datasource.username}") String dataSourceUser,
            @Value("${spring.datasource.password}") String dataSourcePassword,
            @Value("${spring.datasource.url}") String dataSourceUrl) {
        this.repoApiClient = repoApiClient;
        this.frameworkQuotesPath = frameworkQuotesPath;
        this.dataSourceUser = dataSourceUser;
        this.dataSourcePassword = dataSourcePassword;
        this.dataSourceUrl = dataSourceUrl;
    }

    @Async("boundedTaskExecutor")
    public void runExecution(String id){
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
                            String.format("-DpathToQuotes=%s", frameworkQuotesPath),
                            String.format("-Dpostgres.username=%s", dataSourceUser),
                            String.format("-Dpostgres.password=%s", dataSourcePassword),
                            String.format("-Dpostgres.url=%s", dataSourceUrl),
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
