package com.rest_api.fs14backend.exceptions.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



public class AuthorCannotBeDeletedException extends RuntimeException {
    public AuthorCannotBeDeletedException() {
        super("Cannot Delete Author!");
    }
}