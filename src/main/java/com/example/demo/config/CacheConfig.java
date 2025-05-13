package com.example.demo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    private final CacheTtlProperties ttl;

    public CacheConfig(CacheTtlProperties ttl) {
        this.ttl = ttl;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // Different caches with different TTLs
        CaffeineCache authTokenCache1 = new CaffeineCache("authTokenCache1",
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .build());

        CaffeineCache authTokenCache2 = new CaffeineCache("authTokenCache2",
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .build());

        // secretsCache -> 1 hour TTL (different TTL for AWS Secrets)
        CaffeineCache secretsCache = new CaffeineCache("secretsCache",
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .build());

        // ✅ New map-style cache
        CaffeineCache genericKeyValueCache = new CaffeineCache("genericKeyValueCache",
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .build());

        cacheManager.setCaches(Arrays.asList(authTokenCache1, authTokenCache2, secretsCache,genericKeyValueCache));
        return cacheManager;
    }
}
