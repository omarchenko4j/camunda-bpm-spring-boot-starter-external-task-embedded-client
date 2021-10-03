package io.github.omarchenko4j.camunda.externaltask;

import org.camunda.bpm.engine.externaltask.ExternalTask;

/**
 * An interface for a custom implementation of a handler that is called for an external task by topic name.
 *
 * @author Oleg Marchenko
 */
public interface ExternalTaskHandler {
    /**
     * Provides the topic name for the external task in Camunda.
     *
     * @return topic name for the external task
     */
    String topicName();

    /**
     * Executes the logic for handling an external task from Camunda.
     *
     * @param externalTask external task for handling
     */
    void handle(ExternalTask externalTask);
}
