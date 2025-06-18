package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SingletonService {
    private int counter = 0;

    public int incrementAndGet() {
        return ++counter;
    }
}
