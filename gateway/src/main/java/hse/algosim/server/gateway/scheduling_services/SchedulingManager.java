package hse.algosim.server.gateway.scheduling_services;

import feign.FeignException;
import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Component
@Slf4j
public class SchedulingManager {

    private final RepoApiClient repoApiClient;
    private final String repoBasePath;
    private final List<SchedulingService> schedulingServices;

    @Autowired
    public SchedulingManager(
            RepoApiClient repoApiClient,
            @Value("${repo.basePath}") String repoBasePath,
            List<SchedulingService> schedulingServices){
        this.repoApiClient = repoApiClient;
        this.repoBasePath = repoBasePath;
        this.schedulingServices = schedulingServices;
    }

    public void scheduleCodeAnalysis(String id, MultipartFile code) {
        File f = new File(id).getAbsoluteFile();
        try {
            code.transferTo(f);
            repoApiClient.createAlgorithmCode(id,f);
            repoApiClient.createAlgorithmStatus(id, SrcStatus.builder().status(SrcStatus.StatusEnum.SOURCE_UPLOADED).build());
        } catch (FeignException e){
            if (e.status() == 409){
                repoApiClient.updateAlgorithmCode(id,f);
                repoApiClient.updateAlgorithmStatus(id, SrcStatus.builder().status(SrcStatus.StatusEnum.SOURCE_UPLOADED).build());
            } else {
                throw e;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        f.delete();
    }

    @PostConstruct
    private void algorithmStatusUpdateListeners() {
        WebClient client = WebClient.builder()
                .defaultHeaders(header -> header.setBasicAuth(System.getenv("API_CLIENT_USER"), System.getenv("API_CLIENT_PASSWORD")))
                .baseUrl(repoBasePath).build();
        ParameterizedTypeReference<ServerSentEvent<String>> type
                = new ParameterizedTypeReference<>() {
        };

        Flux<ServerSentEvent<String>> eventStream = client.get()
                .uri("/statusUpdateSSE")
                .retrieve()
                .bodyToFlux(type);
        eventStream
                .subscribe(
                        content -> handleStatusUpdate(content),
                        error -> log.error("Error receiving SSE: ", error),
                        () -> log.info("Completed!!!"));
    }

    private void handleStatusUpdate(ServerSentEvent<String> content){
        log.info("Server-sent event: name[{}], content[{}] ",
                content.event(), content.data());

        StatusEnum statusEnum = SrcStatus.StatusEnum.valueOf(content.event());
        for (SchedulingService service: schedulingServices){
            if (service.cahHandle(statusEnum)){
                service.handle(statusEnum,content.data());
                return;
            }
        }
        throw  new IllegalStateException(String.format("No one handles status %s", content.event()));
    }

    @Scheduled(fixedDelay = 2000)
    public void repeatableSchedulingProcess(){
        for (SchedulingService service: schedulingServices){
            service.attemptToExecuteScheduling();
        }
    }
}
