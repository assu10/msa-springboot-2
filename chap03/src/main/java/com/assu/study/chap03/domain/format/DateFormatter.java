package com.assu.study.chap03.domain.format;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter implements Formatter<Date> { // Formatter 인터페이스 구현

    // SimpleDateFormat 은 멀티 스레드에 안전하지 않으므로 클래스 속성으로 사용하면 안되는 대표적인 클래스임
    private SimpleDateFormat sdf;

    public DateFormatter(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            throw new IllegalArgumentException("pattern is null.");
        }

        this.sdf = new SimpleDateFormat(pattern);
    }

    @Override
    public String of(Date target) {
        return sdf.format(target);
    }

    // 클래스 변수인 SimpleDateFormat sdf 의 parse() 를 실행하므로 멀티 스레드 환경에 안전하지 않음
    public Date parse(String dateString) throws ParseException {
        return sdf.parse(dateString);
    }
}
