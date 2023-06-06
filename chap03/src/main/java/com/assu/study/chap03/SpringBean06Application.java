package com.assu.study.chap03;

import com.assu.study.chap03.lifecycle.LifeCycleComponent;
import com.assu.study.chap03.lifecycle.PrintableBeanPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringBean06Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean06Application.class, args);
        ctxt.close();
    }

    // Spring bean 생성 후 customInit() 실행, 애플리케이션 종류 전 customClear() 실행
    @Bean(initMethod = "customInit", destroyMethod = "customClear")
    public LifeCycleComponent lifeCycleComponent() {
        return new LifeCycleComponent();
    }

    @Bean   // PrintableBeanPostProcessor 을 Spring bean 으로 사용
    public BeanPostProcessor beanPostProcessor() {
        return new PrintableBeanPostProcessor();
    }
}
