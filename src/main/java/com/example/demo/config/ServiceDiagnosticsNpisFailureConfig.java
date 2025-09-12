package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "service-diagnostics-failure-config")
public class ServiceDiagnosticsNpisFailureConfig {

    private Map<String, String> npis;

    public Map<String, String> getNpis() {
        return npis;
    }

    public void setNpis(Map<String, String> npis) {
        this.npis = npis;
    }
}

