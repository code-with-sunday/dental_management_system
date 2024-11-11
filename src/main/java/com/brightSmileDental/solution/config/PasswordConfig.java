package com.brightSmileDental.solution.config;


import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;


@Configuration
public class PasswordConfig {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    public static boolean isValid(String password) {
        return password.matches(PASSWORD_PATTERN) && password.length() >= 8 && password.length() <= 20;
    }

}
