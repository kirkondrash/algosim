package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.client.repo.api.RepoApiClient;
import hse.algosim.server.model.SrcStatus;
import hse.algosim.server.model.SrcStatus.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
class ResultsSchedulingService implements SchedulingService{

    private final List<StatusEnum> applicableStatuses = List.of(StatusEnum.SUCCESSFULLY_EXECUTED);
    private final RepoApiClient repoApiClient;

    @Autowired
    public ResultsSchedulingService(RepoApiClient repoApiClient) {
        this.repoApiClient = repoApiClient;
    }

    @Override
    public boolean cahHandle(StatusEnum status) {
        return applicableStatuses.contains(status);
    }

    @Override
    public void handle(StatusEnum status, String id) {
        SrcStatus srcStatus = repoApiClient.readAlgorithmStatus(id).getBody();
        log.info("{}: {}", id, srcStatus.toString());
    }
}
