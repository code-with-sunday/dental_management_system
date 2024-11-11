package com.brightSmileDental.solution.DTO.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicAffiliationResponse {
    private Long clinicId;
    private String officeAddress;
    private String workingDays;
    private String workingHours;
}
