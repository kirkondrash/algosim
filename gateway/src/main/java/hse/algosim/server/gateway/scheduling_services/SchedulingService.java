package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.server.model.SrcStatus.StatusEnum;

import java.util.Queue;
import java.util.function.Function;

public interface SchedulingService {

    boolean cahHandle(StatusEnum status);

    void handle(StatusEnum status, String id);

    default void attemptToExecuteScheduling() {};
}
