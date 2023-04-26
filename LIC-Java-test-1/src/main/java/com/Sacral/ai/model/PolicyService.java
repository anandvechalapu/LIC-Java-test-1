package com.Sacral.ai.model;

import com.Sacral.ai.dto.PolicyServiceRequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PolicyService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public PolicyService() {
    }

    public PolicyService(PolicyServiceRequestDto policyServiceRequestDto) {
        this.id = policyServiceRequestDto.getId();
        this.serviceType = policyServiceRequestDto.getServiceType();
    }
}