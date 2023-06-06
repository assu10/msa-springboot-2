package com.assu.study.chap03;

import com.assu.study.chap03.domain.format.Formatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class SpringBean02Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean02Application.class, args);

        // Spring bean 이름이 localDateTimeFormatter, 클래스 타입은 Formatter.class 인 Spring bean 을 받아서 Formatter.class 타입의 객체 리턴
        // Formatter.class 대신 자식 클래스인 LocalDateTimeFormatter.class 를 넣어도 됨
        Formatter<LocalDateTime> formatter = ctxt.getBean("localDateTimeFormatter", Formatter.class);
        String date = formatter.of(LocalDateTime.of(2023,05,9,23,59,59));

        log.info("date: {}", date); // date: 2023-05-09T23:59:59

        ctxt.close();
    }
}
