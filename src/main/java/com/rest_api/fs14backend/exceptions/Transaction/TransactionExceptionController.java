package com.rest_api.fs14backend.exceptions.Transaction;

import com.rest_api.fs14backend.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class TransactionExceptionController {
    @ExceptionHandler(value = TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionEntity handleNotFound(TransactionNotFoundException exception) {
        return new ExceptionEntity( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = TransactionBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity handleBadInputRequest(TransactionBadInputRequestException authorBadInputRequestException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), authorBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = TransactionCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity handleCannotDelete(TransactionCannotBeDeletedException authorCannotBeDeletedException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), authorCannotBeDeletedException.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionEntity handleMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ExceptionEntity( HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage());
    }

}
