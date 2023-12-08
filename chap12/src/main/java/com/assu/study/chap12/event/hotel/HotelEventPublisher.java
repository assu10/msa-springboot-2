package com.assu.study.chap12.event.hotel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// 게시 클래스
@Slf4j
@Component
public class HotelEventPublisher {
    // ApplicationEventPublisher 스프링 빈 주입받음
    private final ApplicationEventPublisher applicationEventPublisher;

    public HotelEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // 이벤트가 발생하는 클래스에서 호출
    public void publishHotelCreated(Long hotelId, String addr) {
        HotelCreateEvent event = HotelCreateEvent.of(hotelId, addr);
        log.info("Publish hotel created event.");

        // HotelCreateEvent 이벤트 메시지 객체 게시
        applicationEventPublisher.publishEvent(event);
    }
}
