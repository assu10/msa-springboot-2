package com.assu.study.chap12_1.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// 게시 클래스
@Slf4j
@Component
public class UserEventPublisher {
    // ApplicationEventPublisher 스프링 빈 주입받음
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // 이벤트가 발생하는 클래스에서 호출
    public void publishUserCreated(Long userId, String email) {
        UserEvent userEvent = UserEvent.created(this, userId, email);
        log.info("Publish user created event.");

        // UserEvent 이벤트 메시지 객체 게시
        applicationEventPublisher.publishEvent(userEvent);
    }
}
