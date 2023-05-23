package com.rest_api.fs14backend.exceptions.Genre;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GenreCannotBeUpdated extends RuntimeException {
    public GenreCannotBeUpdated(String message) {
        super(message);
    }
}