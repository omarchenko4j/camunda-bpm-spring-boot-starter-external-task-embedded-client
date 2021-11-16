package io.github.omarchenko4j.camunda.embeddedclient.history;

import org.camunda.bpm.engine.impl.history.AbstractHistoryLevel;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.history.event.HistoryEventType;

import static org.camunda.bpm.engine.impl.history.event.HistoryEventTypes.EXTERNAL_TASK_CREATE;

/**
 * @author Oleg Marchenko
 */
public final class HistoryLevelDelegate extends AbstractHistoryLevel {
    private final HistoryLevel targetHistoryLevel;

    public HistoryLevelDelegate(HistoryLevel targetHistoryLevel) {
        this.targetHistoryLevel = targetHistoryLevel;
    }

    @Override
    public int getId() {
        return targetHistoryLevel.getId();
    }

    @Override
    public String getName() {
        return targetHistoryLevel.getName();
    }

    @Override
    public boolean isHistoryEventProduced(HistoryEventType eventType, Object entity) {
        return targetHistoryLevel.isHistoryEventProduced(eventType, entity) || eventType == EXTERNAL_TASK_CREATE;
    }
}
