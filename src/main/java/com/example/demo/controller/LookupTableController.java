package com.example.demo.controller;

import com.example.demo.service.LookupTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LookupTableController {

    @Autowired
    private LookupTableService lookupTableService;

    @GetMapping("/services/{xIdentity}")
    public List<String> getServiceNames(@PathVariable String xIdentity) {
        return lookupTableService.getServiceNamesByXIdentity(xIdentity);
    }
}
