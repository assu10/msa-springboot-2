package com.assu.study.chap03.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PrintableBeanPostProcessor implements BeanPostProcessor {
    // Spring bean 생성 후 실행
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("lifeCycleComponent".equals(beanName)) {
            log.info("[PrintableBeanPostProcessor] - Called postProcessBeforeInitialization() from BeanPostProcessor for : {}", beanName);
        }
        return bean;
    }

    // Spring bean 생성 후 실행
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("lifeCycleComponent".equals(beanName)) {
            log.info("[PrintableBeanPostProcessor] - Called postProcessAfterInitialization() from BeanPostProcessor for : {}", beanName);
        }
        return bean;
    }
}
