package com.example.demo.controller;

import com.example.demo.config.EndpointsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigTestController {
    private final EndpointsConfig endpointsConfig;

    public ConfigTestController(EndpointsConfig endpointsConfig) {
        this.endpointsConfig = endpointsConfig;
    }

    @GetMapping("/ipne")
    public EndpointsConfig.Ipne getIpneConfig() {
        return endpointsConfig.getIpne();
    }

    @GetMapping("/npis")
    public EndpointsConfig.Npis getNpisConfig() {
        return endpointsConfig.getNpis();
    }
}
