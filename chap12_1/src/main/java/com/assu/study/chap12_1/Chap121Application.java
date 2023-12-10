package com.assu.study.chap12_1;

import com.assu.study.chap12_1.server.ApplicationEventListener;
import com.assu.study.chap12_1.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Chap121Application {

    public static void main(String[] args) {
        // SpringApplication 객체와 ApplicationContext 를 설정할 수 있는 메서드를 제공하는 SpringApplicationBuilder 클래스 사용
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Chap121Application.class);
        // build() 를 호출하여 SpringApplication 객체 생성
        SpringApplication application = builder.build();
        // addListener() 는 하나 이상의 ApplicationListener 객체들을 받음
        application.addListeners(new ApplicationEventListener());

        // run() 을 이용하여 스프링 부트 애플리케이션 실행
        ConfigurableApplicationContext ctxt = application.run(args);

        UserService userService = ctxt.getBean(UserService.class);
        userService.createUser("Assu", "assu@test.com");
    }

}
