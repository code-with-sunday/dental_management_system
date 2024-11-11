package com.brightSmileDental.solution.DTO.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorAffiliationRequest {
    private Long doctorId;
    private Long clinicId;
    private String officeAddress;
    private String workingDays;
    private String workingHours;
}
