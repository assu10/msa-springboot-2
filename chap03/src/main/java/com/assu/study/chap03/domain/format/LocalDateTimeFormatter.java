package com.assu.study.chap03.domain.format;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component  // Formatter 인터페이스를 구현한 LocalDateTimeFormatter 클래스를 Spring bean 으로 정의
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public String of(LocalDateTime target) {
        return Optional.ofNullable(target).map(formatter::format).orElse(null);
    }
}
