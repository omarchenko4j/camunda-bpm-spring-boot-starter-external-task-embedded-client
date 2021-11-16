package io.github.omarchenko4j.camunda.embeddedclient.externaltask;

import org.camunda.bpm.engine.externaltask.ExternalTask;

/**
 * The class allows executing logic after locking an external task from Camunda.
 * For correct operation, you must provide the worker ID and the lock duration.
 * <br />It must be extending by class that is <strong>Spring bean</strong>
 * and annotated {@link io.github.omarchenko4j.camunda.embeddedclient.annotation.ExternalTaskTopicName}.
 *
 * @author Oleg Marchenko
 *
 * @see org.camunda.bpm.engine.ExternalTaskService#lock
 */
public abstract class ExternalTaskLockingHandler extends AbstractExternalTaskHandler {
    protected final String workerId;
    protected final long lockDuration;

    protected ExternalTaskLockingHandler(String workerId, long lockDuration) {
        this.workerId = workerId;
        this.lockDuration = lockDuration;
    }

    @Override
    public final void handle(ExternalTask externalTask) {
        externalTaskService.lock(externalTask.getId(), workerId, lockDuration);
        handleAfterLocking(externalTask);
    }

    /**
     * Executes the logic after locking an external task from Camunda.
     *
     * @param externalTask external task for handling after locking
     */
    protected abstract void handleAfterLocking(ExternalTask externalTask);
}
