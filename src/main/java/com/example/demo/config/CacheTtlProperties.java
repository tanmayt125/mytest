package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cache")
public class CacheTtlProperties {
    private int authTokenCache1TtlMinutes;
    private int authTokenCache2TtlMinutes;
    private int secretsCacheTtlHours;
    private int genericKeyValueCacheTtlMinutes;

    // Getters and Setters
    public int getAuthTokenCache1TtlMinutes() {
        return authTokenCache1TtlMinutes;
    }

    public void setAuthTokenCache1TtlMinutes(int authTokenCache1TtlMinutes) {
        this.authTokenCache1TtlMinutes = authTokenCache1TtlMinutes;
    }

    public int getAuthTokenCache2TtlMinutes() {
        return authTokenCache2TtlMinutes;
    }

    public void setAuthTokenCache2TtlMinutes(int authTokenCache2TtlMinutes) {
        this.authTokenCache2TtlMinutes = authTokenCache2TtlMinutes;
    }

    public int getSecretsCacheTtlHours() {
        return secretsCacheTtlHours;
    }

    public void setSecretsCacheTtlHours(int secretsCacheTtlHours) {
        this.secretsCacheTtlHours = secretsCacheTtlHours;
    }

    public int getGenericKeyValueCacheTtlMinutes() {
        return genericKeyValueCacheTtlMinutes;
    }

    public void setGenericKeyValueCacheTtlMinutes(int genericKeyValueCacheTtlMinutes) {
        this.genericKeyValueCacheTtlMinutes = genericKeyValueCacheTtlMinutes;
    }
}
