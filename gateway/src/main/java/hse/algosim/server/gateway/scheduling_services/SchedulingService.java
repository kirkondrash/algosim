package hse.algosim.server.gateway.scheduling_services;

import hse.algosim.server.model.SrcStatus.StatusEnum;

import java.util.Queue;
import java.util.function.Function;

public interface SchedulingService {

    boolean cahHandle(StatusEnum status);

    void handle(StatusEnum status, String id);

    default void attemptToExecuteScheduling(Queue<String> scheduledIds, Function<String, Boolean> schedulingAction){
        for (String scheduledId: scheduledIds){
            if (schedulingAction.apply(scheduledId)) {
                scheduledIds.remove(scheduledId);
            } else {
                break;
            }
        }
    }

}
