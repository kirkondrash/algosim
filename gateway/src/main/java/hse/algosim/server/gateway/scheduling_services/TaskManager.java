package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.client.api.ApiException;
import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@Component
@Slf4j
public class TaskManager {

    private RepoApiClientInstance repoApiClient;
    private List<SchedulingService> schedulingServices;

    @Autowired
    public TaskManager(RepoApiClientInstance repoApiClient,List<SchedulingService> schedulingServices){
        this.repoApiClient = repoApiClient;
        this.schedulingServices = schedulingServices;
    }

    public void scheduleCodeAnalysis(String id, MultipartFile code) throws ApiException {
        File f = new File(id).getAbsoluteFile();
        try {
            code.transferTo(f);
            repoApiClient.createAlgorithmCode(id,f);
            repoApiClient.createAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SOURCE_UPLOADED));
        } catch (ApiException ae){
            if (ae.getCode() == 409){
                repoApiClient.updateAlgorithmCode(id,f);
                repoApiClient.updateAlgorithmStatus(id, new SrcStatus().status(SrcStatus.StatusEnum.SOURCE_UPLOADED));
            } else {
                throw ae;
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
                .baseUrl("http://localhost:8081/api").build();
        ParameterizedTypeReference<ServerSentEvent<String>> type
                = new ParameterizedTypeReference<>() {
        };

        Flux<ServerSentEvent<String>> eventStream = client.get()
                .uri("/stream-sse-mvc")
                .retrieve()
                .bodyToFlux(type);
        eventStream
                .subscribe(
                        content -> handleStatusUpdate(content),
                        error -> log.error("Error receiving SSE: {}", error),
                        () -> log.info("Completed!!!"));
    }

    private void handleStatusUpdate(ServerSentEvent<String> content){
        log.info("Server-sent event: name[{}], id [{}], content[{}] ",
                content.event(), content.id(), content.data());

        StatusEnum statusEnum = SrcStatus.StatusEnum.valueOf(content.event());
        for (SchedulingService service: schedulingServices){
            if (service.cahHandle(statusEnum)){
                service.handle(statusEnum,content.data());
                return;
            }
        }
        throw  new IllegalStateException(String.format("No one handles status %s", content.event()));
    }
}
