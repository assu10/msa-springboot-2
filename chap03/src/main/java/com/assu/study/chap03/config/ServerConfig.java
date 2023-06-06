package com.assu.study.chap03.config;

import com.assu.study.chap03.domain.format.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 자바 설정 클래스임, 따라서 내부에 @Bean Spring bean 정의 가능
public class ServerConfig {
    @Bean   // String 타입이고, 이름이 'datePattern' 인 Spring bean 정의, 날짜 패턴을 의미하는 문자열 객체 리턴
    public String datePattern() {
        return "yyyy-MM-dd'T'HH:mm:ss.XXX";
    }

    @Bean   // DateFormatter 타입이고 이름이 'defaultDateFormatter` 인 Spring bean 정의
    public DateFormatter defaultDateFormatter() {
        // datePattern 에 의존성이 있음
        // datePattern() 메서드를 바로 사용하면 Spring bean 참조를 통해 Spring bean 주입이 됨
        return new DateFormatter(datePattern());
    }
}
