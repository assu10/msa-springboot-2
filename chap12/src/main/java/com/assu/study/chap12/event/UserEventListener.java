package com.assu.study.chap12.event;

import com.assu.study.chap12.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// 구독 클래스
@Slf4j
@Component
public class UserEventListener implements ApplicationListener<UserEvent> {  // ApplicationListener 의 제네릭 클래스 타입은 구독할 이벤트의 클래스 타입

    // 기능을 실행할 스프링 빈 주입
    // UserEventListener 구독 클래스는 UserEvent 를 구독하는 기능만 담당하고, 실제 이벤트 처리는 별도의 클래스(EventService)에 위임
    private final EventService eventService;

    public UserEventListener(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void onApplicationEvent(UserEvent event) {
        if (UserEvent.Type.CREATE == event.getType()) {
            log.info("Listen CREATE event. {}, {}", event.getUserId(), event.getEmail());
            // 메일 발송
            eventService.sendEventEmail(event.getEmail());
        } else if (UserEvent.Type.DELETE == event.getType()) {
            log.info("Listen DELETE event.");
        } else {
            log.error("Unsupported event type. {}", event.getType());
        }
    }
}
