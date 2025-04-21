package com.example.demo.service;

import com.example.demo.entity.ServiceEntity;
import com.example.demo.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class XIdentityService {

    @Autowired
    private ServiceRepository serviceRepository;
    public boolean isServiceAvailable(String xIdentity,String testName){
        Optional<ServiceEntity> serviceEntity = serviceRepository.findById(xIdentity);
        System.out.println(serviceEntity);
        if(serviceEntity.isPresent()){
            String availableTest = "";
            return availableTest.equals(testName);
        }
        System.out.println("Test is not available");
        return false;
    }

}
