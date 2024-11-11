package com.brightSmileDental.solution.DTO.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String ssnLast4;
    private String gender;
    private VisitResponse nextAppointment;
    private List<VisitResponse> visitHistory;
}
