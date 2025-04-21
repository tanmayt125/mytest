package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lookup_table", schema = "ren") // Maps to ren.lookup_table
public class LookupTable {

    @Id
    @Column(name = "\"xIdentity\"")  // ✅ Fix: Enclose in double quotes to match PostgreSQL
    private String xIdentity;

//    @Convert(converter = StringListConverter.class)
    @Column(name = "\"serviceName\"", columnDefinition = "text[]")
    private List<String> serviceName;

    // Constructors
    public LookupTable() {}

    public LookupTable(String xIdentity, List<String> serviceName) {
        this.xIdentity = xIdentity;
        this.serviceName = serviceName;
    }

    // Getters and Setters
    public String getXIdentity() {
        return xIdentity;
    }

    public void setXIdentity(String xIdentity) {
        this.xIdentity = xIdentity;
    }

    public List<String> getServiceName() {
        return serviceName;
    }

    public void setServiceName(List<String> serviceName) {
        this.serviceName = serviceName;
    }
}
