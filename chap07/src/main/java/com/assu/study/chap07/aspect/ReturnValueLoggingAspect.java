package com.assu.study.chap07.aspect;

import com.assu.study.chap07.controller.HotelResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Aspect
public class ReturnValueLoggingAspect {
  // 대상 객체의 메서드가 리턴하는 객체를 어드바이스 메서드의 인자로 받을 수 있도록 변수명 설정
  // Object 클래스 타입으로 받아 메서드 내부에서 타입 변환을 할 수도 있지만 List<HotelResponse> 처럼 클래스 타입을 정의하여 받아도 됨
  @AfterReturning(pointcut = "execution(* getHotelsByName(..))", returning = "customReturnValues")
  public void printReturnObject(JoinPoint joinPoint, List<HotelResponse> customReturnValues) {
    customReturnValues.stream()
        .forEach(response -> log.info("return value: {}", response));
  }

  // 대상 객체의 메서드가 던지는 예외 객체를 어드바이스 메서드의 인자로 받을 수 있도록 변수명 설정
  @AfterThrowing(pointcut = "execution(* getHotelsByName(..))", throwing = "customThrowable")
  public void printThrowable(JoinPoint joinPoint, Throwable customThrowable) {
    log.error("error processing", customThrowable);
  }
}
