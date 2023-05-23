package com.rest_api.fs14backend.exceptions.book;


public class BookBadInputRequestException extends RuntimeException {
    public BookBadInputRequestException() {
        super("Book cannot be created/updated! please check input");
    }
}
