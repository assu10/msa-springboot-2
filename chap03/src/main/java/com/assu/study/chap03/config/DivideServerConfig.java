package com.assu.study.chap03.config;

import com.assu.study.chap03.domain.format.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 자바 설정 파일
public class DivideServerConfig {
    @Bean
    public DateFormatter localDateFormatter(String localDatePattern1) {
        return new DateFormatter(localDatePattern1);
    }
}
