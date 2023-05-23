package com.rest_api.fs14backend.exceptions.book;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found");
    }
}

