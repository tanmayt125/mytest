package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SERVICE_ENTITY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
    @Id
    @Column(name = "X_IDENTITY")
    private String xIdentity;

    @Column(name = "SERVICE")
    private String service;

//    public String getxIdentity() {
//        return xIdentity;
//    }
//
//    public void setxIdentity(String xIdentity) {
//        this.xIdentity = xIdentity;
//    }
//
//    public String getService() {
//        return service;
//    }
//
//    public void setService(String service) {
//        this.service = service;
//    }


}
