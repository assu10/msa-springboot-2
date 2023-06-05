package com.assu.study.chap03;

import com.assu.study.chap03.domain.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Locale;

@Slf4j
@SpringBootApplication
public class SpringBean01Application {
    public static void main(String[] args) {
        // SpringApplication.run() 메서드가 리턴하는 ApplicationContext 를 ctxt 변수에 대입
        // ApplicationContext 는 Spring bean Container 이며, SpringBean01Application 클래스를 설정 파일로 로딩함
        // ApplicationContext 는 자바 설정 클래스를 스캔하며, SpringBean01Application 도 자바 설정 클래스이므로 로딩됨
        // 따라서 아래 설정된 Spring bean 을 로딩할 수 있음
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean01Application.class, args);

        // Spring bean 객체를 defaultPriceUnit 변수에 저장
        // Spring bean Container 는 이름이 'priceUnit' 이고, 타입이 PriceUnit.class 인 Spring bean 객체를 찾아서 리턴함
        PriceUnit defaultPriceUnit = ctxt.getBean("priceUnit", PriceUnit.class);
        log.info("Price #1: {}", defaultPriceUnit.format(BigDecimal.valueOf(10,2)));    // Price #1: $0.10

        // Spring bean 이름이 wonPriceUnit 이고, 타입이 PriceUnit.class 인 Spring bean 을 찾아 wonPriceUnit 변수에 주입
        PriceUnit wonPriceUnit = ctxt.getBean("wonPriceUnit", PriceUnit.class);
        log.info("Price #1: {}", wonPriceUnit.format(BigDecimal.valueOf(10000)));   // Price #1: ₩10,000
    }

    // Spring bean 이름은 priceUnit
    @Bean(name = "priceUnit")
    public PriceUnit dollarPriceUnit() {    // 이 메서드의 리턴 타입인 PriceUnit 클래스가 Spring bean 의 클래스 타입으로 설정됨
        // 이 메서드가 리턴하는 new PriceUnit(Locale.US) 가 Spring bean 객체로 설정됨
        return new PriceUnit(Locale.US);
    }

    // Spring bean name 이 생략되었으므로 메서드명인 wonPriceUnit 이 Spring bean 이름으로 설정됨
    @Bean
    public PriceUnit wonPriceUnit() {
        return new PriceUnit(Locale.KOREA);
    }
}
