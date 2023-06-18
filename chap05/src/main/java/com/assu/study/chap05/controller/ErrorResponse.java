package com.assu.study.chap05.controller;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
  private String errorMessage;;

  public ErrorResponse(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
