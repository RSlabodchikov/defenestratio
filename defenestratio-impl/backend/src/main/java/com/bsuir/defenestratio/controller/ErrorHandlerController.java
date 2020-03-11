package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.exceptions.ChallengeNotFoundException;
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

    @ExceptionHandler(ChallengeNotFoundException.class)
    private ResponseEntity handleChallengeNotFoundException(
            final ChallengeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.NOT_FOUND);
    }
}
