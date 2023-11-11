package com.assu.study.chap11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;

public class CronExpressionTest {
    @Test
    void testParse() {
        // 크론 표현식을 파싱하여 CronExpression 객체 생성
        CronExpression expression = CronExpression.parse("0/5 * * * * ?");
        // next() 는 인자로 받은 시간의 다음 스케쥴링 시간을 응답
        LocalDateTime nextScheduled = expression.next(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        Assertions.assertEquals("2023-01-01T00:00:05", nextScheduled.toString());
        Assertions.assertEquals("2023-01-01T00:00:10", expression.next(nextScheduled).toString());
    }
}
