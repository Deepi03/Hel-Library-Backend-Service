package com.rest_api.fs14backend.exceptions.User.controllerAdvice;

import com.rest_api.fs14backend.ResponseEnt;
import com.rest_api.fs14backend.exceptions.User.LoginCredentialsNotMatchException;
import com.rest_api.fs14backend.exceptions.User.UserBadInputRequestException;
import com.rest_api.fs14backend.exceptions.User.UserCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEnt handleNotFound(UserNotFoundException exception) {
        return new ResponseEnt( HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(value = UserBadInputRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleBadInputRequest(UserBadInputRequestException userBadInputRequestException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), userBadInputRequestException.getMessage());
    }

    @ExceptionHandler(value = UserCannotBeDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleCannotDelete(UserCannotBeDeletedException userCannotBeDeletedException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), userCannotBeDeletedException.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage());
    }
    @ExceptionHandler(value = LoginCredentialsNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnt handleLoginException(LoginCredentialsNotMatchException loginCredentialsNotMatchException) {
        return new ResponseEnt( HttpStatus.BAD_REQUEST.value(), loginCredentialsNotMatchException.getMessage());
    }
}
