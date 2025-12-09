package com.example.demo.kafkaCaching;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeine() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager manager = new CaffeineCacheManager("oauthTokenCache");
        manager.setCaffeine(caffeine);
        return manager;
    }
}

