package com.rest_api.fs14backend.exceptions.Book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookCannotBeCreatedException extends RuntimeException {
    public BookCannotBeCreatedException(String message) {
        super(message);
    }
}