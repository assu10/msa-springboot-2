package com.assu.study.chap09.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
  private boolean success;
  private String resultMessage;
  // data 속성은 API 마다 다를 수 있으므로 제네릭 타입 사용
  private T data;

  public ApiResponse() {
  }

  public ApiResponse(boolean success, String resultMessage) {
    this.success = success;
    this.resultMessage = resultMessage;
  }

  public static <T> ApiResponse ok(T data) {
    ApiResponse apiResponse = new ApiResponse(true, "success");
    apiResponse.data = data;
    return apiResponse;
  }
}
