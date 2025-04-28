package com.example.demo.controller;

import com.example.demo.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/get-auth-token-url1")
    public String getAuthTokenUrl1() {
        return authTokenService.getAuthTokenForUrl1();
    }

    @GetMapping("/get-auth-token-url2")
    public String getAuthTokenUrl2() {
        return authTokenService.getAuthTokenForUrl2();
    }
}
