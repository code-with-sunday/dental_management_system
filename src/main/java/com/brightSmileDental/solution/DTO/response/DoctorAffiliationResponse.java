package com.brightSmileDental.solution.DTO.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorAffiliationResponse {
    private Long id;
    private Long doctorId;
    private Long clinicId;
    private String officeAddress;
    private String workingDays;
    private String workingHours;
}