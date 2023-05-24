package com.rest_api.fs14backend.exceptions.User;


public class UserCannotBeDeletedException extends RuntimeException {
    public UserCannotBeDeletedException() {
        super("Cannot Delete User!");
    }
}