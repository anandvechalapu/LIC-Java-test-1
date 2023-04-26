package com.Sacral.ai.controller;

import com.Sacral.ai.dto.CommonResponseDto;
import com.Sacral.ai.dto.PolicyServiceDto;
import com.Sacral.ai.dto.PolicyServiceRequestDto;
import com.Sacral.ai.exception.ApplicationException;
import com.Sacral.ai.exception.RequiredFieldException;
import com.Sacral.ai.exception.RequestValidationException;
import com.Sacral.ai.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyServiceController.class);

    @Autowired
    private PolicyService policyService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<PolicyServiceDto>> getPolicyServiceById(@PathVariable("id") Long id) throws RequestValidationException, RequiredFieldException, ApplicationException {
        PolicyServiceRequestDto policyServiceRequestDto = new PolicyServiceRequestDto();
        policyServiceRequestDto.setId(id);
        CommonResponseDto<PolicyServiceDto> response = policyService.getPolicyServiceById(policyServiceRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}