package com.brightSmileDental.solution.DTO.request;

import com.brightSmileDental.solution.enums.Speciality;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    private Set<Speciality> specialties;
    private Set<Long> clinicIds;
}
