package com.example.demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Cacheable(value = "authTokenCache1")
    public String getAuthTokenForUrl1() {
        System.out.println("Fetching fresh auth token for URL1...");
        return "AuthToken_URL1_" + System.currentTimeMillis();
    }

    @Cacheable(value = "authTokenCache2")
    public String getAuthTokenForUrl2() {
        System.out.println("Fetching fresh auth token for URL2...");
        return "AuthToken_URL2_" + System.currentTimeMillis();
    }
}

