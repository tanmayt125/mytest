package com.example.demo.controller;

import com.example.demo.config.JoltSpecConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jolt")
public class JoltController {

    @Autowired
    private JoltSpecConfig config;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/transform")
    public Object transform() throws Exception {
        // Parse Jolt spec from config
        List<Object> specJson = mapper.readValue(config.getSpec(), List.class);

        // Create Chainr
//        Chainr chainr = Chainr.fromSpec(specJson);
//
//        // Apply transformation
//        return chainr.transform(input);
        return specJson;
    }
}

