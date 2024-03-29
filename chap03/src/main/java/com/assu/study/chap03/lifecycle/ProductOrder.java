package com.assu.study.chap03.lifecycle;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ProductOrder {
    private BigDecimal orderAmount;
    private LocalDateTime orderAt;
    private String buyerName;

    public ProductOrder(BigDecimal orderAmount, LocalDateTime orderAt, String buyerName) {
        if (orderAmount == null || orderAt == null || StringUtils.isEmpty(buyerName)) {
            throw new IllegalArgumentException("args is null~");
        }

        this.orderAmount = orderAmount;
        this.orderAt = orderAt;
        this.buyerName = buyerName;
    }
}
