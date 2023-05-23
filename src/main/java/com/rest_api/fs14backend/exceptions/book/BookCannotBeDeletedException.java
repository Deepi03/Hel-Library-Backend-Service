package com.rest_api.fs14backend.exceptions.book;


public class BookCannotBeDeletedException extends RuntimeException {
    public BookCannotBeDeletedException() {
        super("Cannot Delete Book!");
    }
}