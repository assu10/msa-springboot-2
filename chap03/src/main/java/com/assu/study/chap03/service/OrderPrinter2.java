package com.assu.study.chap03.service;

import com.assu.study.chap03.domain.format.Formatter;
import com.assu.study.chap03.lifecycle.ProductOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.StringJoiner;

@Service
public class OrderPrinter2 implements Printer<ProductOrder> {
    private Formatter formatter;

    // 해당 클래스에 public 생성자가 하나만 있으므로 @Autowired 가 없어도 의존성 주입
    public OrderPrinter2(@Qualifier("localDateTimeFormatter") Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void print(OutputStream out, ProductOrder productOrder) throws IOException {
        StringJoiner joiner = new StringJoiner("\r\n");
        joiner.add(productOrder.getBuyerName());
        joiner.add(productOrder.getOrderAmount().toPlainString());
        joiner.add(formatter.of(productOrder.getOrderAt()));

        out.write(joiner.toString().getBytes());
    }
}
