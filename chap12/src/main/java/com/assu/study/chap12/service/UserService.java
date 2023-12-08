package com.assu.study.chap12.service;

import com.assu.study.chap12.event.UserEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    private final UserEventPublisher userEventPublisher;

    public UserService(UserEventPublisher userEventPublisher) {
        this.userEventPublisher = userEventPublisher;
    }

    public Boolean createUser(String userName, String email) {
        // 사용자 생성 로직 생략
        log.info("created user. {}, {}", userName, email);

        userEventPublisher.publishUserCreated(11111L, email);
        log.info("done create user event publish.");

        return Boolean.TRUE;
    }
}
