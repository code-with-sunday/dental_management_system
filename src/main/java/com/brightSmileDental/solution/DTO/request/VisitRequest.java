package com.brightSmileDental.solution.DTO.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitRequest {
    private Long patientId;
    private Long doctorId;
    private Long clinicId;
    private LocalDateTime visitDate;
    private String proceduresDone;
    private String doctorNotes;
}
