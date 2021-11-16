package io.github.omarchenko4j.camunda.embeddedclient.externaltask;

import org.camunda.bpm.engine.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * Base class for handling external tasks that provides access to {@link ExternalTaskService}.
 * <br />It must be extending by class that is <strong>Spring bean</strong>
 * and annotated {@link io.github.omarchenko4j.camunda.embeddedclient.annotation.ExternalTaskTopicName}.
 *
 * @author Oleg Marchenko
 */
public abstract class AbstractExternalTaskHandler implements ExternalTaskHandler {
    protected ExternalTaskService externalTaskService;

    @Lazy // Prevents circular dependency.
    @Autowired
    public void setExternalTaskService(ExternalTaskService externalTaskService) {
        this.externalTaskService = externalTaskService;
    }
}
