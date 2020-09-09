package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.client.repo.api.RepoApiClientInstance;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
        System.out.println(String.format("%s: %s", id, srcStatus.toString()));
    }
}
