package com.assu.study.chap12.event.hotel;

import com.assu.study.chap12.service.PropagationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 구독 클래스
@Slf4j
@Component
public class HotelEventListener {

    // 기능을 실행할 스프링 빈 주입
    // HotelEventListener 구독 클래스는 구독만 하고, 실제 이벤트 처리는 별도의 클래스(PropagationService)에 위임
    private final PropagationService propagationService;

    public HotelEventListener(PropagationService propagationService) {
        this.propagationService = propagationService;
    }

    @Async
    @Order(1)
    // 하나 이상의 클래스 타입 설정 가능
    // 정의된 이벤트 메시지가 게시되면 @EventListener 애너테이션이 정의된 메서드가 이벤트를 구독하고 실행함
    @EventListener(value = HotelCreateEvent.class)
    // 구독한 이벤트 객체를 메서드의 인자로 받을 수 있음
    public void handleHotelCreateEvent(HotelCreateEvent hotelCreateEvent) {
        log.info("handle HotelCreatedEvent : {}", hotelCreateEvent);
        propagationService.propagateHotelEvent();
    }

    @Async
    @Order(2)
    @EventListener(value = HotelCreateEvent.class)
    public void handleResourceCreateEvent(HotelCreateEvent hotelCreateEvent) {
        log.info("handle resourceCreatedEvent : {}", hotelCreateEvent);
        propagationService.propagateResourceEvent();
    }
}

