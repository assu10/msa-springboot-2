package com.assu.study.chap03.service;

import com.assu.study.chap03.domain.format.Formatter;
import com.assu.study.chap03.lifecycle.ProductOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.StringJoiner;

@Slf4j
@Service    // 의존성 주입을 위해 스테레오 타입 애너테이션으로 Spring bean 으로 정의
public class OrderPrinter implements Printer<ProductOrder> {

    // 1. 필드 주입 (추천하지 않음)
    // 의존성 주입 대상인 Formatter 도 Spring bean 으로 정의되어 있어야 함
    @Autowired
    @Qualifier("localDateTimeFormatter")    // 주입된 Spring bean 이름을 localDateTimeFormatter 로 설정
    private Formatter formatter01;


    // 2. Setter 메서드 주입
    private Formatter formatter02;

    @Autowired
    public void setFormatter02(@Qualifier("localDateTimeFormatter") Formatter formatter) {
        this.formatter02 = formatter;
    }

    // 3. 생성자 주입 (추천)
    private Formatter formatter03;

    @Autowired
    public OrderPrinter(@Qualifier("localDateTimeFormatter") Formatter formatter) {
        this.formatter03 = formatter;
    }

    @Override
    public void print(OutputStream out, ProductOrder productOrder) throws IOException {
        StringJoiner joiner = new StringJoiner("\r\n");
        joiner.add(productOrder.getBuyerName());
        joiner.add(productOrder.getOrderAmount().toPlainString());
        joiner.add(formatter03.of(productOrder.getOrderAt()));

        out.write(joiner.toString().getBytes());
    }
}
