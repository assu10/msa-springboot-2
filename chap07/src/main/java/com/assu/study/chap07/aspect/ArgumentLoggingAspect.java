package com.assu.study.chap07.aspect;

import com.assu.study.chap07.controller.HotelRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect // 관점 클래스임을 선언
@Component  // 스프링 빈으로 정의, 스프링 AOP 를 사용하여 프록시 객체를 생성하기 때문에 관점 클래스도 스프링 빈이어야 함
public class ArgumentLoggingAspect {

  // 포인트 컷은 HotelRequest 인자를 받는 모든 메서드들
  // 그래서 포인트 컷 표현식에 리턴 타입, 패키지 경로, 클래스명, 메서드명을 지정하는 대신 * 지정
  // 메서드에 HotelRequest 인자가 있는 메서드만 포인트 컷으로 정의해야 하므로 인자 위치에 HotelRequest 클래스 사용
  @Before("execution(* *(com.assu.study.chap07.controller.HotelRequest, ..))")
  public void printHotelRequestArgument(JoinPoint joinPoint) {
    
    log.info("class: {}, method: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

    String argumentValue = Arrays.stream(joinPoint.getArgs()) // Stream<Object> 리턴, 조인 포인트의 인자를 배열로 응답
        .filter(obj -> HotelRequest.class.equals(obj.getClass())) // Stream<Object> 리턴, 인자들 중에서 HotelRequest.class 와 같은 클래스 타입인 객체만 필터링
        .findFirst()  // Optional<Object> 리턴
        .map(HotelRequest.class::cast)  // Optional<HotelRequest> 리턴
        .map(hotelRequest -> hotelRequest.toString()) // Optional<String> 리턴
        .orElseThrow();

    log.info("argument info: {}", argumentValue);
  }
}