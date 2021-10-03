package io.github.omarchenko4j.camunda.externaltask;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Oleg Marchenko
 */
public final class ExternalTaskHandlerRegistry {
    private final Map<String, ExternalTaskHandler> externalTaskHandlers;

    public ExternalTaskHandlerRegistry(Collection<ExternalTaskHandler> externalTaskHandlers) {
        this.externalTaskHandlers =
                externalTaskHandlers.stream()
                                    .collect(Collectors.toMap(ExternalTaskHandler::topicName, Function.identity()));
    }

    public ExternalTaskHandler getExternalTaskHandler(String topicName) {
        return externalTaskHandlers.get(topicName);
    }
}
