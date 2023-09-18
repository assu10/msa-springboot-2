package com.assu.study.chap06.resolver;

import com.assu.study.chap06.controller.ClientInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

// X-SPRING-CHANNEL 와 X-FORWARDED-FOR 헤더를 사용하여 ClientInfo 로 변환하는 리졸버
public class ClientInfoArgumentResolver implements HandlerMethodArgumentResolver {
  private static final String HEADER_CHANNEL = "X-SPRING-CHANNEL";
  private static final String HEADER_CLIENT_IP = "X-FORWARDED-FOR";

  // 컨트롤러 클래스의 핸들러 메서드에 포함된 인자가 ArgumentResolver 구현체의 변환대상인지 확인
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    // 컨트롤러 클래스의 핸들러 메서드에 포함된 인자가 ClientInfo.class 타입이면 ClientInfoArgumentResolver 가 동작하여
    // ClientInfo 인자에 데이터 바인딩
    return ClientInfo.class.equals(parameter.getParameterType());
  }

  // 사용자의 요청 메시지를 특정 객체로 변환
  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    String channel = webRequest.getHeader(HEADER_CHANNEL);
    String clientAddress = webRequest.getHeader(HEADER_CLIENT_IP);
    return new ClientInfo(channel, clientAddress);
  }
}
