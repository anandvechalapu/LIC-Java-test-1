package com.Sacral.ai.repository;

import com.Sacral.ai.dto.CommonResponseDto;
import com.Sacral.ai.dto.PolicyServiceDto;
import com.Sacral.ai.dto.PolicyServiceRequestDto;
import com.Sacral.ai.exception.ApplicationException;
import com.Sacral.ai.exception.RequiredFieldException;
import com.Sacral.ai.exception.RequestValidationException;
import com.Sacral.ai.service.MergerSaveServiceImpl;
import com.Sacral.ai.service.PolicySrvSearchByIdServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyServiceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyServiceRepository.class);

    private final MergerSaveServiceImpl mergerSaveServiceImpl;
    private final PolicySrvSearchByIdServiceImpl policySrvSearchByIdServiceImpl;

    public PolicyServiceRepository(MergerSaveServiceImpl mergerSaveServiceImpl, PolicySrvSearchByIdServiceImpl policySrvSearchByIdServiceImpl) {
        this.mergerSaveServiceImpl = mergerSaveServiceImpl;
        this.policySrvSearchByIdServiceImpl = policySrvSearchByIdServiceImpl;
    }

    public CommonResponseDto<PolicyServiceDto> getPolicyServiceById(PolicyServiceRequestDto policyServiceRequestDto) {
        try {
            validateRequest(policyServiceRequestDto);
            PolicyServiceDto policyServiceDto = retrievePolicyServiceDetails(policyServiceRequestDto);
            return new CommonResponseDto<>(policyServiceDto, null);
        } catch (ApplicationException | RequiredFieldException | RequestValidationException e) {
            LOGGER.error("Error while retrieving policy service details for policy service id {}", policyServiceRequestDto.getId(), e);
            return new CommonResponseDto<>(null, e.getMessage());
        }
    }

    private void validateRequest(PolicyServiceRequestDto policyServiceRequestDto) throws RequestValidationException, RequiredFieldException {
        if (policyServiceRequestDto.getId() == null) {
            throw new RequiredFieldException("policy service id is required");
        }
        if (policyServiceRequestDto.getServiceType() == null) {
            throw new RequiredFieldException("service type is required");
        }
    }

    private PolicyServiceDto retrievePolicyServiceDetails(PolicyServiceRequestDto policyServiceRequestDto) throws ApplicationException {
        if (policyServiceRequestDto.getServiceType().equals("POLSRV_TYPE")) {
            return null;
        }
        if (policyServiceRequestDto.getServiceType().equals("POLSRV_MEMBER_TRASFER_IN_OUT_TYPE")) {
            return mergerSaveServiceImpl.getPolicyServiceById(policyServiceRequestDto.getId());
        } else {
            return policySrvSearchByIdServiceImpl.getPolicyServiceById(policyServiceRequestDto.getId());
        }
    }

}