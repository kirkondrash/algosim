package hse.algosim.server.executor;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.model.SrcStatus;
import hse.algosim.client.repo.api.RepoApiClientInstance;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExecutorServer {

    public static void runExecution(RepoApiClientInstance repoApiClient, UUID id){
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
    }
}
