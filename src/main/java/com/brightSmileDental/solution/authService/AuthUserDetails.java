package com.brightSmileDental.solution.authService;


import com.brightSmileDental.solution.DTO.request.LoginRequest;
import com.brightSmileDental.solution.DTO.request.StaffSignUpRequest;
import com.brightSmileDental.solution.DTO.request.UserSignUpRequest;
import com.brightSmileDental.solution.DTO.response.AuthResponse;

public interface AuthUserDetails {

    AuthResponse signIn( LoginRequest loginRequest);
    AuthResponse userSignup( UserSignUpRequest userSignUpRequest) throws Exception;
    AuthResponse adminSignup( StaffSignUpRequest staffSignUpRequest) throws Exception;

}

