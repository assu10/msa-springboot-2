package com.assu.study.chap05.domain;

// RuntimeException 을 상속하여 Unchecked Exception 으로 설계
// 따라서 불필요한 예외 처리 구문을 사용하지 않음
public class BadRequestException extends RuntimeException {
  // 클라이언트에 전달할 목적
  private String errorMessage;

  public BadRequestException(String errorMessage) {
    super();
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
