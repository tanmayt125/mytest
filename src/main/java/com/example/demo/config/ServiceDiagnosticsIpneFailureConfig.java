package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "service-diagnostics-failure-config")
public class ServiceDiagnosticsIpneFailureConfig {

    private Map<String, String> ipne;

    public Map<String, String> getIpne() {
        return ipne;
    }

    public void setIpne(Map<String, String> ipne) {
        this.ipne = ipne;
    }
}

