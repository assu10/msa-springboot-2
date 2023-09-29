package com.assu.study.chap07.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
@Aspect
@Order(2) // 다른 여러 관점 클래스의 위빙 순서 결정
public class ElapseLoggingAspect {

  //@Around("execution(* com.assu.study.chap07.*.*(..))") // 어드바이스를 적용할 위치(=조인 포인트)를 선정하는 포인트 컷 설정
  @Around("@annotation(ElapseLoggable)")
  public Object loggingPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    // 대상 객체의 메서드가 실행되기 전 실행될 로직
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    log.info("start time clock.");

    Object result;
    try {
      // 대상 객체의 메서드 실행
      result = proceedingJoinPoint.proceed();
    } finally {
      // 대상 객체의 메서드가 실행된 후 실행될 로직
      stopWatch.stop();
      String methodName = proceedingJoinPoint.getSignature().getName();
      long elapsedTime = stopWatch.getLastTaskTimeMillis();
      log.info("{}, elapsed time: {} ms. ", methodName, elapsedTime);
    }

    // proceedingJoinPoint.proceed() 의 결과값을 반드시 리턴해야 함
    return result;
  }
}
