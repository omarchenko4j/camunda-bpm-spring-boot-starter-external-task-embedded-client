package io.github.omarchenko4j.camunda.embeddedclient.plugin;

import io.github.omarchenko4j.camunda.embeddedclient.event.ExternalTaskCreatedEventHandler;
import io.github.omarchenko4j.camunda.embeddedclient.history.HistoryLevelDelegate;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.history.HistoryLevel;

/**
 * @author Oleg Marchenko
 */
public final class EmbeddedClientProcessEnginePlugin extends AbstractProcessEnginePlugin {
    private final ExternalTaskCreatedEventHandler externalTaskCreatedEventHandler;

    public EmbeddedClientProcessEnginePlugin(ExternalTaskCreatedEventHandler externalTaskCreatedEventHandler) {
        this.externalTaskCreatedEventHandler = externalTaskCreatedEventHandler;
    }

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        processEngineConfiguration.getCustomHistoryEventHandlers().add(externalTaskCreatedEventHandler);
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        HistoryLevel historyLevel = new HistoryLevelDelegate(processEngineConfiguration.getHistoryLevel());
        processEngineConfiguration.setHistoryLevel(historyLevel);
    }
}
