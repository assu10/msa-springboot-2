package com.assu.study.chap12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync    // 비동기 기능 활성화
@Configuration
public class AsyncExecutionConfig implements AsyncConfigurer {
    // 프레임워크가 스레드 풀을 설정할 때 사용하는 콜백 메서드
    @Override
    public Executor getAsyncExecutor() {
        return getExecutor();
    }

    private Executor getExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 기본 개수는 10개
        threadPoolTaskExecutor.setCorePoolSize(10);
        // 최대 10개까지 늘어남
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setThreadNamePrefix("asyncExecutor-");
        // 컨테이너가 모든 속성값을 적용한 후 initialize() 호출
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }

    @Bean("asyncExecutor")
    public Executor asyncExecutor() {
        return getExecutor();
    }
}
