package com.rest_api.fs14backend.exceptions.author;

import com.rest_api.fs14backend.ExceptionEntity;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Author not found");
    }
}

