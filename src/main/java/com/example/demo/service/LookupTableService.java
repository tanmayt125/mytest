package com.example.demo.service;

import com.example.demo.entity.LookupTable;
import com.example.demo.repository.LookupTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LookupTableService {

    @Autowired
    private LookupTableRepository lookupTableRepository;

    public List<String> getServiceNamesByXIdentity(String xIdentity) {
        Optional<LookupTable> entity = lookupTableRepository.findByxIdentity(xIdentity);
        return entity.map(LookupTable::getServiceName).orElse(List.of());
    }
}

