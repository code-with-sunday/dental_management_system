package com.brightSmileDental.solution.service.serviceImpl;

import com.brightSmileDental.solution.DTO.request.ClinicRequest;
import com.brightSmileDental.solution.DTO.response.ClinicResponse;
import com.brightSmileDental.solution.exception.ClinicNotFoundException;
import com.brightSmileDental.solution.exception.UnAuthorizedException;
import com.brightSmileDental.solution.mapper.ClinicMapper;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.repository.ClinicRepository;
import com.brightSmileDental.solution.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;

    @Override
    public ClinicResponse addClinic(ClinicRequest clinicRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnAuthorizedException("Session timed out, please Login first");
        }

        if (!authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UnAuthorizedException("You do not have permission to add a doctor");
        }

        Clinic clinic = clinicMapper.toEntity(clinicRequest);
        Clinic savedClinic = clinicRepository.save(clinic);
        ClinicResponse mappedResponse = clinicMapper.toResponse(savedClinic);
        mappedResponse.setId(savedClinic.getId());
        return mappedResponse;
    }

    @Override
    public ClinicResponse getClinicInfo(Long clinicId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnAuthorizedException("Session timed out, please Login first");
        }

        if (!authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UnAuthorizedException("You do not have permission to add a doctor");
        }

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ClinicNotFoundException("Clinic not found with ID: " + clinicId));
        return clinicMapper.toResponse(clinic);
    }
}