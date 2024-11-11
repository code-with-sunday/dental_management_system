package com.brightSmileDental.solution.DTO.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    private Map<String, String> openingHours;

}
