package com.assu.study.chap11.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduleTask {
    @Scheduled(fixedRate = 1000L)   // 1초 간격으로 태스크 실행
    public void triggerEvent() {
        log.info("Triggered Event.");
    }
}
