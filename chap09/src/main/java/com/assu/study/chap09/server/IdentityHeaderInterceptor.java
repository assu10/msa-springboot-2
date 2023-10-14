package com.assu.study.chap09.server;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

// 사용자 요청 메시지에 X-COMPONENT-ID 헤더가 없으면 헤더를 추가하는 역할
public class IdentityHeaderInterceptor implements ClientHttpRequestInterceptor {
  private static final String COMPONENT_HEADER_NAME = "X-COMPONENT-ID";
  private static final String COMPONENT_HEADER_VALUE = "HOTEL-API";

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    // RestTemplate 에 X-COMPONENT-ID 헤더가 없으면 기본값인 HOTEL-API 헤더값 설정
    request.getHeaders().addIfAbsent(COMPONENT_HEADER_NAME, COMPONENT_HEADER_VALUE);

    // ClientHttpRequestExecution 의 execute() 메서드를 실행하여 다음 인터셉터로 요청 전달
    return execution.execute(request, body);
  }
}
