package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HardwareController {

    @PostMapping("${api.path.hardware}")
    public String startHardware() {
        return "Hardware started";
    }
}
