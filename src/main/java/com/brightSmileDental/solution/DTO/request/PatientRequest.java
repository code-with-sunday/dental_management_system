package com.brightSmileDental.solution.DTO.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String ssnLast4;
    private String gender;
    private Long affiliatedClinicId;
    private Long affiliatedDoctorId;
}

