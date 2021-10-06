package io.github.omarchenko4j.camunda.embeddedclient.configuration;

import io.github.omarchenko4j.camunda.embeddedclient.event.ExternalTaskCreatedEventHandler;
import io.github.omarchenko4j.camunda.embeddedclient.externaltask.ExternalTaskHandler;
import io.github.omarchenko4j.camunda.embeddedclient.externaltask.ExternalTaskHandlerRegistry;
import io.github.omarchenko4j.camunda.embeddedclient.plugin.EmbeddedClientProcessEnginePlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @author Oleg Marchenko
 */
@Configuration
public class EmbeddedClientAutoConfiguration {
    @Bean
    @ConditionalOnBean(ExternalTaskHandler.class)
    public ExternalTaskHandlerRegistry externalTaskHandlerRegistry(Collection<ExternalTaskHandler> externalTaskHandlers) {
        return new ExternalTaskHandlerRegistry(externalTaskHandlers);
    }

    @Bean
    @ConditionalOnBean(ExternalTaskHandlerRegistry.class)
    public EmbeddedClientProcessEnginePlugin embeddedClientProcessEnginePlugin(ExternalTaskHandlerRegistry externalTaskHandlerRegistry) {
        return new EmbeddedClientProcessEnginePlugin(new ExternalTaskCreatedEventHandler(externalTaskHandlerRegistry));
    }
}
