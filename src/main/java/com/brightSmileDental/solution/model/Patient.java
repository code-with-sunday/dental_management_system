package com.brightSmileDental.solution.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "patient")
public class Patient extends AuditBaseEntity{
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String ssnLast4;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "affiliated_clinic_id")
    private Clinic affiliatedClinic;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor affiliatedDoctor;
}
