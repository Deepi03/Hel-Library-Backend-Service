package com.rest_api.fs14backend.exceptions.book.controllerAdvice;

import com.rest_api.fs14backend.ExceptionEntity;
import com.rest_api.fs14backend.exceptions.book.BookBadInputRequestException;
import com.rest_api.fs14backend.exceptions.book.BookCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.book.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class BookExceptionController {
    @ExceptionHandler(value = BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionEntity handleNotFound(BookNotFoundException exception) {
        return new ExceptionEntity( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = BookBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity handleBadInputRequest(BookBadInputRequestException authorBadInputRequestException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), authorBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = BookCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity handleCannotDelete(BookCannotBeDeletedException authorCannotBeDeletedException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), authorCannotBeDeletedException.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity handleMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage());
    }

}
