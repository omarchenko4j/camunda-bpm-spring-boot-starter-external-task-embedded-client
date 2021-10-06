package io.github.omarchenko4j.camunda.embeddedclient.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Oleg Marchenko
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExternalTaskTopicName {
    String value();
}
