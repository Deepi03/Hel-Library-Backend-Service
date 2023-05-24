package com.rest_api.fs14backend.exceptions.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}

