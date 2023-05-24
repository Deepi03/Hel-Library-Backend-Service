package com.rest_api.fs14backend.exceptions.book;


public class BookBadInputRequestException extends RuntimeException {
    public BookBadInputRequestException() {
        super("Given Book title or isbn already exist");
    }
}
