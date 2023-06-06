package com.assu.study.chap03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DividePatternConfig {
    @Bean
    public String localDatePattern1() {
        return "yyyy-MM-dd'T'HH:mm:ss";
    }
}
