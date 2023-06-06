package com.assu.study.chap03;

import com.assu.study.chap03.domain.format.DateFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class SpringBean05Application {
    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBean05Application.class, args);

        // ThreadPoolConfig.java 설정에 따라 스레드 개수는 10개, 큐 크기는 500이다.
        ThreadPoolTaskExecutor taskExecutor = applicationContext.getBean(ThreadPoolTaskExecutor.class);

        final String dateString = "2023-05-05T23:59:59.-08:00";
        for (int i=0; i<100; i++) {
            taskExecutor.submit(() -> {
                try {
                    // ThreadPoolTaskExecutor 에 의해 실행되는 스레드 내부에서 singletonDateFormatter Spring bean 을 ApplicationContext 로부터 가져옴
                    // 따라서 멀티스레드로 동작함
                    DateFormatter formatter = applicationContext.getBean("singletonDateFormatter", DateFormatter.class);

                    // parse() 가 멀티 스레드에 안전하다면 출력되는 모든 값은 동일한 날짜가 출력될 것임
                    log.info("Date: {}, hashCode: {}", formatter.parse(dateString), formatter.hashCode());

                } catch(Exception e) {
                    log.error("error to parse", e);
                }
            });
        }
        TimeUnit.SECONDS.sleep(5);
        applicationContext.close();
    }

    // 이름이 singletonDateFormatter, 타입이 DateFormatter 인 Spring bean 설정
    // Scope 는 기본값인 singleton
    // 따라서 여러 곳에 의존성 주입되어도 동일한 하나의 Spring bean 임
    @Bean
    @Scope("prototype")
    public DateFormatter singletonDateFormatter() {
        return new DateFormatter("yyyy-MM-dd'T'HH:mm:ss");
    }
}
