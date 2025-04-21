package com.example.demo.controller;

import com.example.demo.config.AppProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppInfoController {

    private final AppProperties appProperties;

    public AppInfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/app-info")
    public Map<String, String> getAppInfo() {
        Map<String, String> response = new HashMap<>();
        response.put("name", appProperties.getName());
        response.put("version", appProperties.getVersion());
        response.put("author", appProperties.getAuthor());
        return response;
    }
}