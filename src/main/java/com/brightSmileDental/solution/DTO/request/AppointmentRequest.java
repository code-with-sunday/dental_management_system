package com.brightSmileDental.solution.DTO.request;

import com.brightSmileDental.solution.enums.Speciality;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequest {
    private Long patientId;
    private Long doctorId;
    private Long clinicId;
    private Speciality procedure;
    private LocalDateTime appointmentDate;
}
