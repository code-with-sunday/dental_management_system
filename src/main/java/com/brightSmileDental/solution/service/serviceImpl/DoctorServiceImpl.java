package com.brightSmileDental.solution.service.serviceImpl;

import com.brightSmileDental.solution.DTO.request.DoctorRequest;
import com.brightSmileDental.solution.DTO.response.DoctorResponse;
import com.brightSmileDental.solution.exception.ClinicNotFoundException;
import com.brightSmileDental.solution.exception.UnAuthorizedException;
import com.brightSmileDental.solution.mapper.DoctorMapper;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import com.brightSmileDental.solution.repository.ClinicRepository;
import com.brightSmileDental.solution.repository.DoctorRepository;
import com.brightSmileDental.solution.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final ClinicRepository clinicRepository;

    @Override
    public DoctorResponse addDoctor(DoctorRequest doctorRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnAuthorizedException("Session timed out, please Login first");
        }

        if (!authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UnAuthorizedException("You do not have permission to add a doctor");
        }

        Set<Clinic> clinics = doctorRequest.getClinicIds().stream()
                .map(clinicId -> clinicRepository.findById(clinicId)
                        .orElseThrow(() -> new ClinicNotFoundException("Clinic not found with ID: " + clinicId)))
                .collect(Collectors.toSet());

        clinics.forEach(clinic -> {
            if (clinic.getDoctors() == null) {
                clinic.setDoctors(new HashSet<>());
            }
        });

        Doctor doctor = doctorMapper.toEntity(doctorRequest, clinics);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return doctorMapper.toResponse(savedDoctor);
    }

}