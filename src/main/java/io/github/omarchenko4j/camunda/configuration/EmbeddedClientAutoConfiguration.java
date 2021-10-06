package io.github.omarchenko4j.camunda.configuration;

import io.github.omarchenko4j.camunda.event.ExternalTaskCreatedEventHandler;
import io.github.omarchenko4j.camunda.externaltask.ExternalTaskHandler;
import io.github.omarchenko4j.camunda.externaltask.ExternalTaskHandlerRegistry;
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
    public ExternalTaskHandlerRegistry externalTaskCreatedEventHandler(Collection<ExternalTaskHandler> externalTaskHandlers) {
        return new ExternalTaskHandlerRegistry(externalTaskHandlers);
    }

    @Bean
    @ConditionalOnBean(ExternalTaskHandlerRegistry.class)
    public ExternalTaskCreatedEventHandler externalTaskCreatedEventHandler(ExternalTaskHandlerRegistry externalTaskHandlerRegistry) {
        return new ExternalTaskCreatedEventHandler(externalTaskHandlerRegistry);
    }
}