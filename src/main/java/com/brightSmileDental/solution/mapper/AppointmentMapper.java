package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.AppointmentRequest;
import com.brightSmileDental.solution.DTO.response.AppointmentResponse;
import com.brightSmileDental.solution.model.Appointment;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import com.brightSmileDental.solution.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentMapper {

    public AppointmentResponse toResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatient().getId())
                .doctorId(appointment.getDoctor().getId())
                .clinicId(appointment.getClinic().getId())
                .procedure(appointment.getProcedure())
                .appointmentDate(appointment.getAppointmentDate())
                .dateBooked(appointment.getDateBooked())
                .build();
    }

    public Appointment toEntity(AppointmentRequest request, Patient patient, Doctor doctor, Clinic clinic) {
        return Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .clinic(clinic)
                .procedure(request.getProcedure())
                .appointmentDate(request.getAppointmentDate())
                .dateBooked(LocalDateTime.now())
                .build();
    }
}
