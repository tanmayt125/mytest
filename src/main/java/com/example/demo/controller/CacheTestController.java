package com.example.demo.controller;

import com.example.demo.service.AuthTokenService;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class CacheTestController {
    private final AuthTokenService authTokenService;
    private final CacheManager cacheManager;

    public CacheTestController(AuthTokenService authTokenService, CacheManager cacheManager) {
        this.authTokenService = authTokenService;
        this.cacheManager = cacheManager;
    }

    @GetMapping("/map")
    public Map<String, String> getMapFromCache() {
        return authTokenService.getGenericKeyValueMap();
    }

    @DeleteMapping("/map")
    public String clearMapCache() {
        authTokenService.clearGenericKeyValueCache(cacheManager);
        return "genericKeyValueCache cleared.";
    }
}
