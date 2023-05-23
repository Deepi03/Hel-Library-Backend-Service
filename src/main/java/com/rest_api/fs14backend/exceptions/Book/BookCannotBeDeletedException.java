package com.rest_api.fs14backend.exceptions.Book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookCannotBeDeletedException extends RuntimeException {
    public BookCannotBeDeletedException(String message) {
        super(message);
    }
}