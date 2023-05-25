package com.rest_api.fs14backend.exceptions.Genre.controllerAdvice;

import com.rest_api.fs14backend.ResponseEnt;
import com.rest_api.fs14backend.exceptions.Genre.GenreBadInputRequestException;
import com.rest_api.fs14backend.exceptions.Genre.GenreCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.Genre.GenreNotFoundException;
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
public class GenreExceptionController {
    @ExceptionHandler(value = GenreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEnt handleNotFound(GenreNotFoundException exception) {
        return new ResponseEnt( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = GenreBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleBadInputRequest(GenreBadInputRequestException genreBadInputRequestException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), genreBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = GenreCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleCannotDelete(GenreCannotBeDeletedException genreCannotBeDeletedException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), genreCannotBeDeletedException.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage());
    }

}
