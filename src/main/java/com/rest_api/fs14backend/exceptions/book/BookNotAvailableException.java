package com.rest_api.fs14backend.exceptions.book;

public class BookNotAvailableException extends  RuntimeException{
    public BookNotAvailableException() {
        super("Book is not available");
    }
}
