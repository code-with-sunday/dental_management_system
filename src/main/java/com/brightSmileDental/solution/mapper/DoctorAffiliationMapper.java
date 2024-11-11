package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.DoctorAffiliationRequest;
import com.brightSmileDental.solution.DTO.response.DoctorAffiliationResponse;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import com.brightSmileDental.solution.model.DoctorAffiliation;
import org.springframework.stereotype.Component;

@Component
public class DoctorAffiliationMapper {

    public DoctorAffiliationResponse toResponse(DoctorAffiliation affiliation) {
        return DoctorAffiliationResponse.builder()
                .id(affiliation.getId())
                .doctorId(affiliation.getDoctor().getId())
                .clinicId(affiliation.getClinic().getId())
                .officeAddress(affiliation.getOfficeAddress())
                .workingDays(affiliation.getWorkingDays())
                .workingHours(affiliation.getWorkingHours())
                .build();
    }

    public DoctorAffiliation toEntity(DoctorAffiliationRequest request, Doctor doctor, Clinic clinic) {
        return DoctorAffiliation.builder()
                .doctor(doctor)
                .clinic(clinic)
                .officeAddress(request.getOfficeAddress())
                .workingDays(request.getWorkingDays())
                .workingHours(request.getWorkingHours())
                .build();
    }
}
