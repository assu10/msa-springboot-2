package com.assu.study.chap12.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventService {
    public void sendEventEmail(String email) {
        // 이메일 발송
        log.info("Send Email~, {}", email);
    }
}
