package com.assu.study.chap06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.assu.study.chap06")
public class Chap06Application {

  public static void main(String[] args) {
    SpringApplication.run(Chap06Application.class, args);
  }

}
