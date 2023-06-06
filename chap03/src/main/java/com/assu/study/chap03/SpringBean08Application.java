package com.assu.study.chap03;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication
public class SpringBean08Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean08Application.class, args);

        // Spring bean 이름으로 Spring bean 을 전달받아 obj 에 저장
        Object obj = ctxt.getBean("systemId");
        log.info("Bean info. type: {}, value: {}", obj.getClass(), obj);

        ctxt.close();
    }

    @Configuration
    class SystemConfig1 {
        // 클래스 타입이 Long 클래스 타입이고, 이름이 systemId 인 Spring bean 정의
        @Bean
        public Long systemId() {
            return 1L;
        }
    }

    @Configuration
    class SystemConfig2 {
        // 클래스 타입이 String 클래스 타입이고, 이름이 systemId 인 Spring bean 정의
        @Bean
        public String systemId() {
            return new String("hahaha");
        }
    }
}
