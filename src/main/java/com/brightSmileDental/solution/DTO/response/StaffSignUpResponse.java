package com.brightSmileDental.solution.DTO.response;

import com.brightSmileDental.solution.enums.ROLE;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StaffSignUpResponse {

    private String email;
    private String password;
    private ROLE role;
}

