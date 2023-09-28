package com.assu.study.chap06.controller;

import com.assu.study.chap06.domain.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
    System.out.println("Error Message: " + ex.getErrorMessage());

    return new ResponseEntity<>(
        new ErrorResponse(ex.getErrorMessage()),
        HttpStatus.BAD_REQUEST
    );
  }
}
