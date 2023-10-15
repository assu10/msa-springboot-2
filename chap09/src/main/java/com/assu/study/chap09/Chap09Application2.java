package com.assu.study.chap09;

import com.assu.study.chap09.adapter.WebClientBillingAdapter;
import com.assu.study.chap09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Chap09Application2 {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Chap09Application2.class, args);

    WebClientBillingAdapter adapter = ctx.getBean(WebClientBillingAdapter.class);
    CreateCodeResponse codeResponse = adapter.createWithWebClient(List.of(19000L, 18000L, 17000L));

    // Result : CreateCodeResponse(codes=[19000, 18000, 17000])
    log.info("Result : {}", codeResponse);
  }

}
