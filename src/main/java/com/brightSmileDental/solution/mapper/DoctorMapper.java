package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.DoctorRequest;
import com.brightSmileDental.solution.DTO.response.ClinicAffiliationResponse;
import com.brightSmileDental.solution.DTO.response.DoctorResponse;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public DoctorResponse toResponse(Doctor doctor) {
        Set<ClinicAffiliationResponse> affiliations = doctor.getClinics().stream()
                .map(clinic -> new ClinicAffiliationResponse(
                        clinic.getId(),
                        clinic.getAddress(),
                        "",
                        ""
                ))
                .collect(Collectors.toSet());

        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .address(doctor.getAddress())
                .phoneNumber(doctor.getPhoneNumber())
                .email(doctor.getEmail())
                .city(doctor.getCity())
                .state(doctor.getState())
                .specialties(doctor.getSpecialties())
                .build();
    }

    public Doctor toEntity(DoctorRequest request, Set<Clinic> clinics) {
        return Doctor.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .city(request.getCity())
                .state(request.getState())
                .specialties(request.getSpecialties())
                .clinics(clinics)
                .build();
    }
}