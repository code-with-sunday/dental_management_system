package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.PatientRequest;
import com.brightSmileDental.solution.DTO.response.PatientResponse;
import com.brightSmileDental.solution.DTO.response.VisitResponse;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import com.brightSmileDental.solution.model.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientMapper {

    public PatientResponse toResponse(Patient patient, VisitResponse nextAppointment, List<VisitResponse> visitHistory) {
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .address(patient.getAddress())
                .phoneNumber(patient.getPhoneNumber())
                .dateOfBirth(patient.getDateOfBirth())
                .ssnLast4(patient.getSsnLast4())
                .gender(patient.getGender())
                .nextAppointment(nextAppointment)
                .visitHistory(visitHistory)
                .build();
    }

    public Patient toEntity(PatientRequest request, Clinic clinic, Doctor doctor) {
        return Patient.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .ssnLast4(request.getSsnLast4())
                .gender(request.getGender())
                .affiliatedClinic(clinic)
                .affiliatedDoctor(doctor)
                .build();
    }
}
