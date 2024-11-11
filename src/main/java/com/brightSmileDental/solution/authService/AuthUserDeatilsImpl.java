package com.brightSmileDental.solution.authService;


import com.brightSmileDental.solution.DTO.request.LoginRequest;
import com.brightSmileDental.solution.DTO.request.StaffSignUpRequest;
import com.brightSmileDental.solution.DTO.request.UserSignUpRequest;
import com.brightSmileDental.solution.DTO.response.AuthResponse;
import com.brightSmileDental.solution.config.PasswordConfig;
import com.brightSmileDental.solution.enums.ROLE;
import com.brightSmileDental.solution.exception.PasswordCriteriaException;
import com.brightSmileDental.solution.exception.UserAlreadyExistException;
import com.brightSmileDental.solution.model.User;
import com.brightSmileDental.solution.repository.UserRepository;
import com.brightSmileDental.solution.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserDeatilsImpl implements AuthUserDetails{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordConfig passwordConfig;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsImpl customUserDetails;

    @Override
    public AuthResponse userSignup(UserSignUpRequest userSignUpRequest) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(userSignUpRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("Email already exists with another account");
        }
        if (!PasswordConfig.isValid(userSignUpRequest.getPassword())) {
            throw new PasswordCriteriaException("Password must meet the following criteria: " +
                    "at least 8 characters long, " + "contain at least one uppercase letter, " +
                    "one lowercase letter, " + "one digit, and " + "one special character)");
        }
        User user = new User();
        user.setEmail(userSignUpRequest.getEmail());

        user.setEmail(userSignUpRequest.getEmail());
        user.setRole(ROLE.PATIENT);
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        User savedUser = userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return AuthResponse.builder()
                .success(true)
                .message("Register success")
                .httpStatus("200")
                .build();
    }

    @Override
    public AuthResponse adminSignup(StaffSignUpRequest staffSignUpRequest) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(staffSignUpRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("Email already exists with another account");
        }
        if (!PasswordConfig.isValid(staffSignUpRequest.getPassword())) {
            throw new PasswordCriteriaException("Password must meet the following criteria: " +
                    "at least 8 characters long, " + "contain at least one uppercase letter, " +
                    "one lowercase letter, " + "one digit, and " + "one special character)");
        }
        User user = new User();
        user.setEmail(staffSignUpRequest.getEmail());

        user.setEmail(staffSignUpRequest.getEmail());
        user.setRole(staffSignUpRequest.getRole() != null ? staffSignUpRequest.getRole() : ROLE.DOCTOR);
        user.setPassword(passwordEncoder.encode(staffSignUpRequest.getPassword()));
        User savedUser = userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return AuthResponse.builder()
                .success(true)
                .message("Register success")
                .httpStatus("200")
                .build();
    }

    @Override
    public AuthResponse signIn(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.isEmpty() ? ROLE.PATIENT.name()
                : authorities.iterator().next().getAuthority().replace("ROLE_", "");

        String jwt = jwtProvider.generateToken(authentication);
        return AuthResponse.builder()
                .success(true)
                .message("Login success")
                .httpStatus("200")
                .role(ROLE.valueOf(role))
                .jwt(jwt)
                .build();
    }


    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
}
