package com.brightSmileDental.solution.model;

import com.brightSmileDental.solution.enums.Speciality;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "appointment")
public class Appointment extends AuditBaseEntity{
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Enumerated(EnumType.STRING)
    private Speciality procedure;

    private LocalDateTime appointmentDate;
    private LocalDateTime dateBooked;
}
