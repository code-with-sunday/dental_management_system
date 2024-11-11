package com.brightSmileDental.solution.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg){
        super(msg);
    }

}
