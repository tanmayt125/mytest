package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class SecretsCacheRefresher{
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private AwsSecretsService awsSecretsService;

    public void refreshSecret(String secretName) {
        System.out.println("Manually refreshing secret for: " + secretName);

        // Evict the cache manually
        cacheManager.getCache("secretsCache").evict(secretName);

        // Re-fetch and re-cache immediately
        awsSecretsService.getSecret(secretName);
    }
}
