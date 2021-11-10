# Camunda External Task Embedded Client as Spring Boot Starter

---

Spring Boot Starter provides *embedded* client to fast handling of external tasks for 
[Camunda BPM](https://github.com/camunda/camunda-bpm-platform).
An embedded client opens up a *different way* to handle external tasks than 
[camunda-bpm-spring-boot-starter-external-task-client](https://github.com/camunda/camunda-bpm-platform/tree/master/spring-boot-starter/starter-client).
It works on top of thrown *historical events* to avoid long polling when using the *FetchAndLock API*.

## Getting Started

### Requirement

For the embedded client to work correctly, the history level must be set to **FULL**!

```yaml
camunda:
  bpm:
    history-level: FULL
```

### Dependency

#### Maven:
```xml
<dependency>
  <groupId>io.github.omarchenko4j</groupId>
  <artifactId>camunda-bpm-spring-boot-starter-external-task-embedded-client</artifactId>
  <version>0.2.0</version>
</dependency>
```

#### Gradle:
```groovy
compile("io.github.omarchenko4j:camunda-bpm-spring-boot-starter-external-task-embedded-client:0.2.0")
```

### Example

1. We have the following process:

![alt text](https://camunda.com/wp-content/uploads/2021/03/Screenshot-2021-03-30-at-10.49.49-1024x463.png)

2. Implement external task handler:

```java
@Component
@ExternalTaskTopicName("scoreProvider") // Specifies the topic name of external task.
public class ScoreProviderExternalTaskHandler implements ExternalTaskHandler {
    private ExternalTaskService externalTaskService;

    @Lazy // Prevents circular dependency.
    @Autowired
    public void setExternalTaskService(ExternalTaskService externalTaskService) {
        this.externalTaskService = externalTaskService;
    }

    @Override
    public void handle(ExternalTask externalTask) {
        String externalTaskId = externalTask.getId();
        
        externalTaskService.lock(externalTaskId, "WORKER", TimeUnit.SECONDS.toMillis(5)); // Manual lock.
        
        // Some logic of score provider.
        
        externalTaskService.complete(externalTaskId, "WORKER"); // Manual complete.
    }
}
```

3. Start a process instance. It works!

## License

Camunda External Task Embedded Client is Open Source software released under
the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
