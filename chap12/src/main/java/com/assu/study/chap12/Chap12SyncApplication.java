package com.assu.study.chap12;

import com.assu.study.chap12.server.ApplicationEventListener;
import com.assu.study.chap12.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Chap12SyncApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Chap12SyncApplication.class);
        SpringApplication application = builder.build();
        application.addListeners(new ApplicationEventListener());

        ConfigurableApplicationContext ctxt = application.run(args);

        UserService userService = ctxt.getBean(UserService.class);
        userService.createUser("Assu", "assu@test.com");
    }

}
