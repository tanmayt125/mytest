package com.example.demo.controller;

import com.example.demo.config.JoltSpecConfigForJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/jolt")
public class JoltJsonController {

    private final JoltSpecConfigForJson config;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JoltJsonController(JoltSpecConfigForJson config) throws Exception {
        this.config = config;
    }

    @PostMapping("/transformJson")
    public Object transform() throws IOException {
        // Read JSON from file into spec
        List<Object> spec = objectMapper.readValue(
                config.getSpecResource().getInputStream(),
                new TypeReference<List<Object>>() {}
        );
        return spec;
    }
}

