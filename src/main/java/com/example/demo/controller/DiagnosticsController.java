package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosticsController {

    @PostMapping("${api.path.diagnostics}")
    public String startDiagnostics() {
        return "System diagnostics started";
    }
}

