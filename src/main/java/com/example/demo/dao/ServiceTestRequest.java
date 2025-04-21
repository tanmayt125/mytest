package com.example.demo.dao;

import jakarta.validation.constraints.NotNull;

public class ServiceTestRequest {
    @NotNull(message = "test Name can not be null")
    private String testName;
    private String processId;
    private String tenantId;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
