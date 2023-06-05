package com.assu.study.chap03.domain;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Getter
public class PriceUnit {
    private final Locale locale;

    public PriceUnit(Locale locale) {
        if (Objects.isNull(locale)) {
            throw new IllegalArgumentException("locale is null");
        }
        this.locale = locale;
    }

    public String format(BigDecimal price) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(Optional.ofNullable(price).orElse(BigDecimal.ZERO));
    }

    public void validate() {
        if (Objects.isNull(locale)) {
            throw new IllegalArgumentException("local is null2");
        }
        log.info("locale is [{}]", locale);
    }
}
