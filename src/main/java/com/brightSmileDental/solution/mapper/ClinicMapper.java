package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.ClinicRequest;
import com.brightSmileDental.solution.DTO.response.ClinicResponse;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.repository.ClinicRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ClinicMapper {

    private final ClinicRepository clinicRepository;

    public ClinicMapper(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public ClinicResponse toResponse(Clinic clinic) {
        ClinicResponse.ClinicResponseBuilder builder = ClinicResponse.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .address(clinic.getAddress())
                .phoneNumber(clinic.getPhoneNumber())
                .email(clinic.getEmail())
                .city(clinic.getCity())
                .state(clinic.getState())
                .openingHours(clinic.getOpeningHours() != null ? clinic.getOpeningHours() : clinic.getDefaultOpeningHours());

        return builder.build();
    }

    public Clinic toEntity(ClinicRequest request) {
        Clinic clinic = new Clinic();
        clinic.setName(request.getName());
        clinic.setAddress(request.getAddress());
        clinic.setPhoneNumber(request.getPhoneNumber());
        clinic.setEmail(request.getEmail());
        clinic.setCity(request.getCity());
        clinic.setState(request.getState());

        clinic.setOpeningHours(clinic.getDefaultOpeningHours());

        return clinic;
    }

}
