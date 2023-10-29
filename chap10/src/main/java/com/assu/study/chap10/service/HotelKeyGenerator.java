package com.assu.study.chap10.service;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class HotelKeyGenerator implements KeyGenerator {
  private final String PREFIX = "HOTEL::";

  @Override
  // 캐시 key 생성 시 KeyGenerator 구현체가 설정되어 있으면 generate() 메서드를 사용하여 key 생성
  // Object target: 애너테이션이 정의된 클래스 객체
  // Method method: 애너테이션이 정의된 메서드
  // Object... params: 메서드의 인자들
  public Object generate(Object target, Method method, Object... params) {
    if (Objects.isNull(params)) {
      return "NULL";
    }

    return Arrays.stream(params)
        // 인자 중에서 HotelRequest 조회
        .filter(param -> param instanceof HotelRequest)
        .findFirst()
        // HotelRequest 클래스 타입인 인자가 있으면 HOTEL:: 문자열과 hotelRequest 객체의 hotelId 값을 결합하여 key 생성
        .map(obj -> (HotelRequest) obj)
        .map(hotelRequest -> PREFIX + hotelRequest.getHotelId())
        // HotelRequest 클래스 타입인 객체가 없다면 스프링 프레임워크에서 기본으로 사용하는 SimpleKeyGenerator 로 캐시 key 생성
        .orElse(SimpleKeyGenerator.generateKey(params).toString());
  }
}
