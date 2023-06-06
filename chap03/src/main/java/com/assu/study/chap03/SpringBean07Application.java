package com.assu.study.chap03;

import com.assu.study.chap03.lifecycle.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Locale;

@Slf4j
@SpringBootApplication
public class SpringBean07Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean07Application.class, args);

        // Spring bean 이름을 따로 지정하지 않고 클래스 타입으로만 매칭되는 Spring bean 을 가져옴
        PriceUnit priceUnit = ctxt.getBean(PriceUnit.class);
        log.info("Locale in PriceUnit: {}", priceUnit.getLocale().toString());

        ctxt.close();
    }

    @Bean
    @Primary
    // 클래스 타입이 PriceUnit 이고, 이름이 primaryPriceUnit 인 Spring bean 정의
    public PriceUnit primaryPriceUnit() {
        return new PriceUnit(Locale.US);
    }

    @Bean
    // 클래스 타입이 PriceUnit 이고, 이름이 secondPriceUnit 인 Spring bean 정의
    public PriceUnit secondaryPriceUnit() {
        return new PriceUnit(Locale.KOREA);
    }
}
