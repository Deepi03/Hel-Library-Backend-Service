package com.rest_api.fs14backend.exceptions.Genre;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GenreCannotBeDeletedException extends RuntimeException {
    public GenreCannotBeDeletedException() {
        super("Cannot Delete Genre!");
    }
}