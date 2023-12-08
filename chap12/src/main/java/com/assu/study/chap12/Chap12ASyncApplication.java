package com.assu.study.chap12;

import com.assu.study.chap12.service.HotelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Chap12ASyncApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(Chap12ASyncApplication.class, args);

        HotelService hotelService = ctxt.getBean(HotelService.class);
        hotelService.createHotel("ASSU", "Seoul");
    }

}
