package com.rest_api.fs14backend.exceptions.author;


public class AuthorBadInputRequestException extends RuntimeException {
    public AuthorBadInputRequestException() {
        super("Given Author name already exist or bad input");
    }
}
