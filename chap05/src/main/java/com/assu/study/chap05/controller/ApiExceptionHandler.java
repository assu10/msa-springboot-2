package com.assu.study.chap05.controller;

import com.assu.study.chap05.domain.BadRequestException;
import com.assu.study.chap05.domain.FileDownloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @ExceptionHandler 가 정의된 메서드를 포함하는 Spring bean 애너테이션
public class ApiExceptionHandler {

  @ExceptionHandler(BadRequestException.class)  // BadRequestException 이 발생하면 처리할 메서드 지정
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {  //
    System.out.println("Error Message: " + ex.getErrorMessage());

    // 클라이언트에 응답 메시지를 전달
    return new ResponseEntity<>(
            new ErrorResponse(ex.getErrorMessage()),
            HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    return new ResponseEntity<>(
            new ErrorResponse("System Error"),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @ExceptionHandler(FileDownloadException.class)
  public ResponseEntity handleFileDownloadException(FileDownloadException e) {
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
