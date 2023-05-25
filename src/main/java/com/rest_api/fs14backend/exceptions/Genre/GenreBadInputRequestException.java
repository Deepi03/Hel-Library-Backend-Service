package com.rest_api.fs14backend.exceptions.Genre;

public class GenreBadInputRequestException extends RuntimeException {
    public GenreBadInputRequestException() {
        super("Given Genre name already exist or bad input");
    }
}