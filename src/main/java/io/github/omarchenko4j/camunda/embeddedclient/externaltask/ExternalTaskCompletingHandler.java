package io.github.omarchenko4j.camunda.embeddedclient.externaltask;

import org.camunda.bpm.engine.externaltask.ExternalTask;

/**
 * The class allows executing logic before completing an external task from Camunda.
 * Before completion, the task will be locked so you need to provide the worker ID and the lock duration.
 * <br>It must be extending by class that is <strong>Spring bean</strong>
 * and annotated {@link io.github.omarchenko4j.camunda.embeddedclient.annotation.ExternalTaskTopicName}.
 *
 * @author Oleg Marchenko
 *
 * @see org.camunda.bpm.engine.ExternalTaskService#complete(String, String)
 */
public abstract class ExternalTaskCompletingHandler extends ExternalTaskLockingHandler {
    protected ExternalTaskCompletingHandler(String workerId, long lockDuration) {
        super(workerId, lockDuration);
    }

    @Override
    protected final void handleAfterLocking(ExternalTask externalTask) {
        handleBeforeCompleting(externalTask);
        externalTaskService.complete(externalTask.getId(), workerId);
    }

    /**
     * Executes the logic before completing an external task from Camunda.
     *
     * @param externalTask external task for handling before completing
     */
    protected abstract void handleBeforeCompleting(ExternalTask externalTask);
}
