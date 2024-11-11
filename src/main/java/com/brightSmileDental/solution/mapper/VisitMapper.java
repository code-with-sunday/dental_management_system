package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.VisitRequest;
import com.brightSmileDental.solution.DTO.response.VisitResponse;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import com.brightSmileDental.solution.model.Patient;
import com.brightSmileDental.solution.model.Visit;
import org.springframework.stereotype.Component;

@Component
public class VisitMapper {

    public VisitResponse toResponse(Visit visit) {
        return VisitResponse.builder()
                .id(visit.getId())
                .patientId(visit.getPatient().getId())
                .doctorId(visit.getDoctor().getId())
                .clinicId(visit.getClinic().getId())
                .visitDate(visit.getVisitDate())
                .proceduresDone(visit.getProceduresDone())
                .doctorNotes(visit.getDoctorNotes())
                .build();
    }

    public Visit toEntity(VisitRequest request, Patient patient, Doctor doctor, Clinic clinic) {
        return Visit.builder()
                .patient(patient)
                .doctor(doctor)
                .clinic(clinic)
                .visitDate(request.getVisitDate())
                .proceduresDone(request.getProceduresDone())
                .doctorNotes(request.getDoctorNotes())
                .build();
    }
}
