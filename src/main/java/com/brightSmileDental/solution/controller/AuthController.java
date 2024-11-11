package com.brightSmileDental.solution.controller;

import com.brightSmileDental.solution.DTO.request.LoginRequest;
import com.brightSmileDental.solution.DTO.request.StaffSignUpRequest;
import com.brightSmileDental.solution.DTO.request.UserSignUpRequest;
import com.brightSmileDental.solution.DTO.response.AuthResponse;
import com.brightSmileDental.solution.authService.AuthUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserDetails authUserDetails;

    @PostMapping("/user/signup")
    public AuthResponse userRegistration(@RequestBody @Valid UserSignUpRequest userSignUpRequest) throws Exception {
        return authUserDetails.userSignup(userSignUpRequest);
    }

    @PostMapping("/admin/signup")
    public AuthResponse adminRegistration(@RequestBody @Valid StaffSignUpRequest staffSignUpRequest) throws Exception {
        return authUserDetails.adminSignup(staffSignUpRequest);
    }

    @PostMapping("/login")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
        return authUserDetails.signIn(loginRequest);
    }

}