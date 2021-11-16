package io.github.omarchenko4j.camunda.embeddedclient.externaltask;

import io.github.omarchenko4j.camunda.embeddedclient.annotation.ExternalTaskTopicName;
import org.apache.commons.lang3.NotImplementedException;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.springframework.core.annotation.AnnotationUtils;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * An interface for a custom implementation of a handler that is called for an external task by topic name.
 * <br>It must be implemented by class that is <strong>Spring bean</strong>
 * and annotated {@link ExternalTaskTopicName}.
 *
 * @author Oleg Marchenko
 */
public interface ExternalTaskHandler {
    /**
     * Provides the topic name for the external task in Camunda.
     *
     * @return topic name for the external task
     */
    default String topicName() {
        ExternalTaskTopicName externalTaskTopicName =
                AnnotationUtils.findAnnotation(getClass(), ExternalTaskTopicName.class);
        if (nonNull(externalTaskTopicName)) {
            String topicName = externalTaskTopicName.value();
            if (isBlank(topicName)) {
                throw new IllegalArgumentException("Topic name must not be empty");
            }
            return topicName;
        }
        else {
            throw new NotImplementedException("Implement this method or provide an @ExternalTaskTopicName annotation");
        }
    }

    /**
     * Executes the logic for handling an external task from Camunda.
     *
     * @param externalTask external task for handling
     */
    void handle(ExternalTask externalTask);
}
