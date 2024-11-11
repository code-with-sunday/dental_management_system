package com.brightSmileDental.solution.DTO.response;

import com.brightSmileDental.solution.enums.ROLE;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse<T> {
    private String jwt;
    private String message;
    private ROLE role;
    private boolean success;
    private String httpStatus;


    public AuthResponse( boolean success, String message, String httpStatus, ROLE role, String jwt) {
        this.success = true;
        this.message = message;
        this.httpStatus = httpStatus;
        this.role = role;
        this.jwt = jwt;
    }

    public AuthResponse(boolean success, String message, String httpStatus) {
        this.success = true;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}