package com.brightSmileDental.solution.mapper;

import com.brightSmileDental.solution.DTO.request.UserSignUpRequest;
import com.brightSmileDental.solution.DTO.response.UserResponse;
import com.brightSmileDental.solution.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User mapToEntity(UserSignUpRequest userRequest) {
        if (userRequest == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}