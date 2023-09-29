package com.assu.study.chap07.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElapseLoggable {  // 포인트 컷을 지정하는 마킹 애너테이션이므로 별도의 속성값을 설정하지 않음
}
