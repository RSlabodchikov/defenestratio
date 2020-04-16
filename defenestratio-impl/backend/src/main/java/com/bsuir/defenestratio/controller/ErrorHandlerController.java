package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.exceptions.CannotBlockUserException;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.exceptions.UsernameDuplicateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    private HttpHeaders headers;


    public ErrorHandlerController() {
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/problem+json");
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity handleChallengeNotFoundException(
            final NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotBlockUserException.class)
    private ResponseEntity handleCannotBlockUserException(
            final CannotBlockUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameDuplicateException.class)
    private ResponseEntity handleUsernameDuplicateException(
            final UsernameDuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.NOT_ACCEPTABLE);
    }
}
