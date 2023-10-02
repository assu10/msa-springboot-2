package com.assu.study.chap07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration  // ThreadPoolConfig 자바 설정 클래스는 애플리케이션이 실행될 때 클래스 내부에 정의된 스프링 빈들이 생성됨
public class ThreadPoolConfig {

  // 스프링 빈 이름은 threadPoolTaskExecutor 이고, ThreadPoolTaskExecutor 클래스 타입
  @Bean(destroyMethod = "shutdown")
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    // 스레드 풀의 개수는 최대 10개까지 늘어남
    threadPoolTaskExecutor.setMaxPoolSize(10);
    // 스레드 풀의 이름은 AsyncExecutor- 로 시작함
    threadPoolTaskExecutor.setThreadNamePrefix("AsyncExecutor-");
    // 컨테이너가 모든 속성값을 적용한 후 initialize() 호출
    threadPoolTaskExecutor.afterPropertiesSet();

    return threadPoolTaskExecutor;
  }
}
