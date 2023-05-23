package com.rest_api.fs14backend.exceptions.author.controllerAdvice;

import com.rest_api.fs14backend.ExceptionEntity;
import com.rest_api.fs14backend.exceptions.author.AuthorBadInputRequestException;
import com.rest_api.fs14backend.exceptions.author.AuthorCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthorExceptionController {
    @ExceptionHandler(value = AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionEntity authorNotFound(AuthorNotFoundException exception) {
        return new ExceptionEntity( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = AuthorBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity authorCannotCreateUpdate(AuthorBadInputRequestException authorBadInputRequestException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), authorBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = AuthorCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity authorCannotDelete(AuthorCannotBeDeletedException authorCannotBeDeletedException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), authorCannotBeDeletedException.getMessage());
    }


}
