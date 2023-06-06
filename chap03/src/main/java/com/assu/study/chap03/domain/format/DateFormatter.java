package com.assu.study.chap03.domain.format;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter implements Formatter<Date> { // Formatter 인터페이스 구현
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

    public Date parse(String dateString) throws ParseException {
        return sdf.parse(dateString);
    }
}
