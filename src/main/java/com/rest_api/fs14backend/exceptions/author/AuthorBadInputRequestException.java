package com.rest_api.fs14backend.exceptions.author;


public class AuthorBadInputRequestException extends RuntimeException {
    public AuthorBadInputRequestException() {
        super("Author cannot be created/updated! please check input");
    }
}
