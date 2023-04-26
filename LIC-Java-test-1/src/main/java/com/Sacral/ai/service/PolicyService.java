package com.Sacral.ai.service;

import com.Sacral.ai.dto.CommonResponseDto;
import com.Sacral.ai.dto.PolicyServiceDto;
import com.Sacral.ai.dto.PolicyServiceRequestDto;
import com.Sacral.ai.exception.ApplicationException;
import com.Sacral.ai.exception.RequiredFieldException;
import com.Sacral.ai.exception.RequestValidationException;
import com.Sacral.ai.repository.PolicyServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyService.class);

    @Autowired
    private PolicyServiceRepository policyServiceRepository;

    public CommonResponseDto<PolicyServiceDto> getPolicyServiceById(PolicyServiceRequestDto policyServiceRequestDto) {
        return policyServiceRepository.getPolicyServiceById(policyServiceRequestDto);
    }

}