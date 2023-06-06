package com.assu.study.chap03;

import com.assu.study.chap03.lifecycle.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Locale;

@Slf4j
@SpringBootApplication
public class SpringBean09Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean09Application.class);

        log.info("---------------- Done to initialize spring beans.");

        PriceUnit priceUnit = ctxt.getBean("lazyPriceUnit", PriceUnit.class);

        log.info("Locale in PriceUnit: {}", priceUnit.getLocale().toString());

        ctxt.close();
    }

    @Bean
    @Lazy   // 클래스 타입은 PriceUnit 이고, 이름은 lazyPriceUnit 인 Spring bean 에 Lazy 적용
    public PriceUnit lazyPriceUnit() {
        log.info("initialize lazyPriceUnit");   // lazyPriceUnit Spring bean 이 생성될 때 로그 출력
        return new PriceUnit(Locale.KOREA);
    }
}
