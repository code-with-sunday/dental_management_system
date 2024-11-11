package com.brightSmileDental.solution.DTO.response;

import com.brightSmileDental.solution.enums.ROLE;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private ROLE role;
}
