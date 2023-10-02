package com.assu.study.chap07.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@TestConfiguration  // ThreadPoolConfigTest 자바 설정 클래스는 테스트가 실행될 때 클래스 내부에 정의된 스프링 빈들이 생성됨
public class ThreadPoolConfigTest {

  // 기존의 threadPoolTaskExecutor 스프링 빈을 재정의하려고 타입과 이름이 같은 스프링 빈 정의
  @Bean(destroyMethod = "shutdown")
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    // 스레드 풀의 개수는 최대 1개까지 늘어남
    threadPoolTaskExecutor.setMaxPoolSize(1);
    // 스레드 풀의 이름은 TestExecutor- 로 시작함
    threadPoolTaskExecutor.setThreadNamePrefix("TestExecutor-");
    // 컨테이너가 모든 속성값을 적용한 후 initialize() 호출
    threadPoolTaskExecutor.afterPropertiesSet();

    return threadPoolTaskExecutor;
  }
}