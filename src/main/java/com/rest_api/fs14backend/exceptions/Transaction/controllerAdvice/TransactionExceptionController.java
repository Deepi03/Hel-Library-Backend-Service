package com.rest_api.fs14backend.exceptions.Transaction.controllerAdvice;

import com.rest_api.fs14backend.ResponseEnt;
import com.rest_api.fs14backend.exceptions.Transaction.TransactionBadInputRequestException;
import com.rest_api.fs14backend.exceptions.Transaction.TransactionCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.Transaction.TransactionNotFoundException;
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
    public ResponseEnt handleNotFound(TransactionNotFoundException exception) {
        return new ResponseEnt( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = TransactionBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleBadInputRequest(TransactionBadInputRequestException authorBadInputRequestException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), authorBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = TransactionCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleCannotDelete(TransactionCannotBeDeletedException authorCannotBeDeletedException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), authorCannotBeDeletedException.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage());
    }

}
