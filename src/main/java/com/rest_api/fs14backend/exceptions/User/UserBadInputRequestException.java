package com.rest_api.fs14backend.exceptions.User;


public class UserBadInputRequestException extends RuntimeException {
    public UserBadInputRequestException() {
        super("User name already exist");
    }
}
