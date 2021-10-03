package io.github.omarchenko4j.camunda.event;

import io.github.omarchenko4j.camunda.externaltask.ExternalTaskHandler;
import io.github.omarchenko4j.camunda.externaltask.ExternalTaskHandlerRegistry;
import io.github.omarchenko4j.camunda.externaltask.ExternalTaskInformation;
import org.camunda.bpm.engine.impl.history.event.HistoricExternalTaskLogEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * @author Oleg Marchenko
 */
public class ExternalTaskCreatedEventHandler implements HistoryEventHandler {
    private final ExternalTaskHandlerRegistry externalTaskHandlerRegistry;

    public ExternalTaskCreatedEventHandler(ExternalTaskHandlerRegistry externalTaskHandlerRegistry) {
        this.externalTaskHandlerRegistry = externalTaskHandlerRegistry;
    }

    @Override
    public void handleEvent(HistoryEvent event) {
        if (event instanceof HistoricExternalTaskLogEntity) {
            HistoricExternalTaskLogEntity externalTaskEvent = (HistoricExternalTaskLogEntity) event;
            if (externalTaskEvent.isCreationLog()) {
                String topicName = externalTaskEvent.getTopicName();

                ExternalTaskHandler externalTaskHandler =
                        externalTaskHandlerRegistry.getExternalTaskHandler(topicName);
                if (nonNull(externalTaskHandler)) {
                    externalTaskHandler.handle(new ExternalTaskInformation(externalTaskEvent));
                }
            }
        }
    }

    @Override
    public void handleEvents(List<HistoryEvent> events) {
        events.forEach(this::handleEvent);
    }
}
