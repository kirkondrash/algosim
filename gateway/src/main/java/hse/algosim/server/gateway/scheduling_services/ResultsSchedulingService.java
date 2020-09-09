package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
class ResultsSchedulingService implements SchedulingService{

    private List<StatusEnum> applicableStatuses = List.of(StatusEnum.SUCCESSFULLY_EXECUTED);
    private RepoApiClientInstance repoApiClient;

    @Autowired
    public ResultsSchedulingService(RepoApiClientInstance repoApiClient) {
        this.repoApiClient = repoApiClient;
    }

    @Override
    public boolean cahHandle(StatusEnum status) {
        return applicableStatuses.contains(status);
    }

    @SneakyThrows
    @Override
    public void handle(StatusEnum status, String id) {
        SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id);
        log.info("{}: {}", id, srcStatus.toString());
    }
}
