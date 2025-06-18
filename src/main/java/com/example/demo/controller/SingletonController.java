package com.example.demo.controller;

import com.example.demo.service.SingletonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingletonController {

    @Autowired
    private SingletonService singletonService;

    @GetMapping("/count")
    public String getCount() {
        int count = singletonService.incrementAndGet();
        return "Current count is: " + count;
    }
}
