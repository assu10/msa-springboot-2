package com.assu.study.chap12.service;

import com.assu.study.chap12.event.hotel.HotelEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelService {
    private final HotelEventPublisher hotelEventPublisher;

    public HotelService(HotelEventPublisher hotelEventPublisher) {
        this.hotelEventPublisher = hotelEventPublisher;
    }

    public Boolean createHotel(String hotelName, String hotelAddr) {
        // 호텔 생성 로직 생략
        log.info("created hotel. {}, {}", hotelName, hotelAddr);
        hotelEventPublisher.publishHotelCreated(2222L, hotelAddr);
        log.info("done create hotel publish.")
        ;
        return Boolean.TRUE;
    }
}
