package com.assu.study.chap03.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class LifeCycleComponent implements InitializingBean, DisposableBean {

    // Spring bean 생성 후 실행
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[LifeCycleComponent] - afterPropertiesSet() from InitializingBean");
    }

    // 애플리케이션 종료 전 실행
    @Override
    public void destroy() throws Exception {
        log.info("[LifeCycleComponent] - destroy() from DisposableBean:");
    }

    public void customInit() {
        log.info("[LifeCycleComponent] - customInit()");
    }

    public void customClear() {
        log.info("[LifeCycleComponent] - customClear()");
    }
}
