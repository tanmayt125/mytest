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

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // Different caches with different TTLs
        CaffeineCache authTokenCache1 = new CaffeineCache("authTokenCache1",
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .expireAfterWrite(5, TimeUnit.MINUTES)
                        .build());

        CaffeineCache authTokenCache2 = new CaffeineCache("authTokenCache2",
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        cacheManager.setCaches(Arrays.asList(authTokenCache1, authTokenCache2));
        return cacheManager;
    }
}
