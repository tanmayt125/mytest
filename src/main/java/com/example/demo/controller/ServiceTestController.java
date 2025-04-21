package com.example.demo.controller;

import com.example.demo.dao.ServiceTestRequest;
import com.example.demo.service.XIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class ServiceTestController {

    @Autowired
    private XIdentityService xIdentityService;
    @PostMapping("/service")
    public ResponseEntity<?> serviceTest(@Valid @RequestBody ServiceTestRequest request, @RequestHeader HttpHeaders headers){
        String xIdentity = headers.getFirst("xIdentity");
        Map<String,Object>response = new HashMap<>();
        response.put("request",request);
        response.put("xIdentity",xIdentity);
        if(xIdentityService.isServiceAvailable(xIdentity,request.getTestName())) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
