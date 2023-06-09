package com.rest_api.fs14backend.exceptions.author.controllerAdvice;

import com.rest_api.fs14backend.ResponseEnt;
import com.rest_api.fs14backend.exceptions.author.AuthorBadInputRequestException;
import com.rest_api.fs14backend.exceptions.author.AuthorCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class AuthorExceptionController {
    @ExceptionHandler(value = AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEnt handleNotFound(AuthorNotFoundException exception) {
        return new ResponseEnt( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = AuthorBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleBadInputRequest(AuthorBadInputRequestException authorBadInputRequestException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), authorBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = AuthorCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleCannotDelete(AuthorCannotBeDeletedException authorCannotBeDeletedException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), authorCannotBeDeletedException.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage());
    }

}
