package io.github.omarchenko4j.camunda.embeddedclient.plugin;

import io.github.omarchenko4j.camunda.embeddedclient.event.ExternalTaskCreatedEventHandler;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

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
}
