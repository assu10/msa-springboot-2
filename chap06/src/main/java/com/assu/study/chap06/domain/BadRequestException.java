package com.assu.study.chap06.domain;

public class BadRequestException extends RuntimeException {
  private final String errorMessage;

  public BadRequestException(String errorMessage) {
    super();
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
