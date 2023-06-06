package com.assu.study.chap03;

import com.assu.study.chap03.lifecycle.ProductOrder;
import com.assu.study.chap03.service.OrderPrinter2;
import com.assu.study.chap03.service.Printer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class SpringBean03Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean03Application.class, args);

        Printer printer = ctxt.getBean(OrderPrinter2.class);
        ProductOrder order = new ProductOrder(BigDecimal.valueOf(1000), LocalDateTime.now(), "assu");

        try (OutputStream os = System.out){
            printer.print(os, order);
//            assu
//            1000
//            2023-06-06T14:14:44
        } catch (IOException e) {
            log.error("error: ", e);
        }
        ctxt.close();
    }
}
