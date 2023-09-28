package com.assu.study.chap06.controller;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
  private final String errorMessage;

  public ErrorResponse(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
