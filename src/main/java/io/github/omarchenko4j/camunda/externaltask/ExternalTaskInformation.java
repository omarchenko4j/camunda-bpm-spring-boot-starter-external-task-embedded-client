package io.github.omarchenko4j.camunda.externaltask;

import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.impl.history.event.HistoricExternalTaskLogEntity;

import java.util.Date;
import java.util.Map;

import static java.util.Collections.emptyMap;

/**
 * @author Oleg Marchenko
 */
public final class ExternalTaskInformation implements ExternalTask {
    private final String id;
    private final String topicName;
    private final String workerId;
    private final String processInstanceId;
    private final String executionId;
    private final String activityId;
    private final String activityInstanceId;
    private final String processDefinitionId;
    private final String processDefinitionKey;
    private final Integer retries;
    private final String errorMessage;
    private final String tenantId;
    private final long priority;

    public ExternalTaskInformation(HistoricExternalTaskLogEntity event) {
        this.id = event.getExternalTaskId();
        this.topicName = event.getTopicName();
        this.workerId = event.getWorkerId();
        this.processInstanceId = event.getProcessInstanceId();
        this.executionId = event.getExecutionId();
        this.activityId = event.getActivityId();
        this.activityInstanceId = event.getActivityInstanceId();
        this.processDefinitionId = event.getProcessDefinitionId();
        this.processDefinitionKey = event.getProcessDefinitionKey();
        this.retries = event.getRetries();
        this.errorMessage = event.getErrorMessage();
        this.tenantId = event.getTenantId();
        this.priority = event.getPriority();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTopicName() {
        return topicName;
    }

    @Override
    public String getWorkerId() {
        return workerId;
    }

    @Override
    public Date getLockExpirationTime() {
        return null;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public String getExecutionId() {
        return executionId;
    }

    @Override
    public String getActivityId() {
        return activityId;
    }

    @Override
    public String getActivityInstanceId() {
        return activityInstanceId;
    }

    @Override
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    @Override
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    @Override
    public String getProcessDefinitionVersionTag() {
        return null;
    }

    @Override
    public Integer getRetries() {
        return retries;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public long getPriority() {
        return priority;
    }

    @Override
    public Map<String, String> getExtensionProperties() {
        return emptyMap();
    }

    @Override
    public String getBusinessKey() {
        return null;
    }
}
