package com.brightSmileDental.solution.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String msg){
        super(msg);
    }
}
