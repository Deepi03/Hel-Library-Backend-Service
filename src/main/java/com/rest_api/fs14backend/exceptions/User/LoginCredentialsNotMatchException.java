package com.rest_api.fs14backend.exceptions.User;

public class LoginCredentialsNotMatchException extends RuntimeException {
    public LoginCredentialsNotMatchException(){
        super("Login credentials not match");
    }
}
