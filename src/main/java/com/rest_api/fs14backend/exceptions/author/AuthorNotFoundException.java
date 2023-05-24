package com.rest_api.fs14backend.exceptions.author;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Author not found");
    }
}

