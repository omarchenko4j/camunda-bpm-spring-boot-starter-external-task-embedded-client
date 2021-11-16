# Camunda External Task Embedded Client as Spring Boot Starter

---

Spring Boot Starter provides *embedded* client to fast handling of external tasks for 
[Camunda BPM](https://github.com/camunda/camunda-bpm-platform).
An embedded client opens up a *different way* to handle external tasks than 
[camunda-bpm-spring-boot-starter-external-task-client](https://github.com/camunda/camunda-bpm-platform/tree/master/spring-boot-starter/starter-client).
It works on top of thrown *historical events* to avoid long polling when using the *FetchAndLock API*.

## Getting Started

### Dependency

#### Maven:
```xml
<dependency>
  <groupId>io.github.omarchenko4j</groupId>
  <artifactId>camunda-bpm-spring-boot-starter-external-task-embedded-client</artifactId>
  <version>0.3.0</version>
</dependency>
```

#### Gradle:
```groovy
compile("io.github.omarchenko4j:camunda-bpm-spring-boot-starter-external-task-embedded-client:0.3.0")
```

### Example

1. We have the following process:

![alt text](https://camunda.com/wp-content/uploads/2021/03/Screenshot-2021-03-30-at-10.49.49-1024x463.png)

2. Implement external task handlers:

2.1 With manually locking and completing an external task:
```java
@Slf4j
@Component
@ExternalTaskTopicName("scoreProvider") // Specifies the topic name of external task.
public class ScoreProviderExternalTaskHandler extends AbstractExternalTaskHandler {
    @Override
    public void handle(ExternalTask externalTask) {
        String externalTaskId = externalTask.getId();
        
        externalTaskService.lock(externalTaskId, "WORKER", TimeUnit.SECONDS.toMillis(5)); // Manual lock.
        
        log.info("Some logic of score provider.");
        
        externalTaskService.complete(externalTaskId, "WORKER"); // Manual complete.
    }
}
```

2.2 Without manually locking but with manually completing an external task:
```java
@Slf4j
@Component
@ExternalTaskTopicName("loanGranter")
public class LoanGranterExternalTaskHandler extends ExternalTaskLockingHandler {
    public LoanGranterExternalTaskHandler() {
        super("WORKER", TimeUnit.SECONDS.toMillis(5));
    }
    
    @Override
    protected void handleAfterLocking(ExternalTask externalTask) {
        log.info("Some logic of loan granter.");
        
        externalTaskService.complete(externalTask.getId(), workerId); // Manual complete.
    }
}
```

2.3 Without manually locking and completing an external task:
```java
@Slf4j
@Component
@ExternalTaskTopicName("requestRejecter")
public class RequestRejecterExternalTaskHandler extends ExternalTaskCompletingHandler {
    public RequestRejecterExternalTaskHandler() {
        super("WORKER", TimeUnit.SECONDS.toMillis(5));
    }
    
    @Override
    protected void handleBeforeCompleting(ExternalTask externalTask) {
        log.info("Some logic of request rejecter.");
    }
}
```

3. Start a process instance. It works!

## License

Camunda External Task Embedded Client is Open Source software released under
the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
