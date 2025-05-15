package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class JoltSpecConfigForJson {
    @Value("${jolt.spec.path}")
    private Resource specResource;

    public Resource getSpecResource() {
        return specResource;
    }
}
