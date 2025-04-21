package com.example.demo.controller;

import com.example.demo.exception.PaytmServerDownException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paytm")
public class Paytm {
    @PostMapping("/pay")
    public String pay()
    {
        throw new PaytmServerDownException("service not available as per RBI Rules");
    }
}
