package com.example.demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Cacheable("myCache")
    public String getData(String param) {
        System.out.println("Fetching fresh data for param: " + param);
        return "Data for param: " + param + " at " + System.currentTimeMillis();
    }
}

