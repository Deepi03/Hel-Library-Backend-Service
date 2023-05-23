package com.rest_api.fs14backend.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserCannotBeDeletedException extends RuntimeException {
    public UserCannotBeDeletedException(String message) {
        super(message);
    }
}