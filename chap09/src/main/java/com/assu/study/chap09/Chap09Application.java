package com.assu.study.chap09;

import com.assu.study.chap09.Adapter.BillingAdapter;
import com.assu.study.chap09.controller.BillingCodeResponse;
import com.assu.study.chap09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Chap09Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Chap09Application.class, args);

    BillingAdapter billingAdapter = ctx.getBean(BillingAdapter.class);

    List<BillingCodeResponse> responses = billingAdapter.getBillingCodes("CODE:1234567");
    // getBillingCode: [{billingCode=CODE-111111}, {billingCode=CODE-222222}]
    log.info("getBillingCode: {}", responses);

    CreateCodeResponse createCodeResponse = billingAdapter.createWithPostForEntity(List.of(1234567L));
    // createWithPostForEntity: CreateCodeResponse(codes=[1234567])
    log.info("createWithPostForEntity: {}", createCodeResponse);

  }

}
