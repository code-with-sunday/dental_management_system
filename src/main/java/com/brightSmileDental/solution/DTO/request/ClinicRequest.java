package com.brightSmileDental.solution.DTO.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
}
