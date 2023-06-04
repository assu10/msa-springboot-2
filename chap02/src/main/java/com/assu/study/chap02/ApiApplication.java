package com.assu.study.chap02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        // Spring bean container 인 ApplicationContext 객체 리턴
        ConfigurableApplicationContext ctx = SpringApplication.run(Chap02Application.class, args);

        // application.properties 의 내용을 가져오기 위해 Environment 객체 가져옴
        // Environment 객체도 Spring bean 객체임
        Environment env = ctx.getBean(Environment.class);
        String portValue = env.getProperty("server.port");
        log.info("My port: {}", portValue);

        // ApplicationContext 객체가 관리하고 있는 Spring bean 들의 이름 조회
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(name -> log.info("Bean name: {}", name));
    }
}
