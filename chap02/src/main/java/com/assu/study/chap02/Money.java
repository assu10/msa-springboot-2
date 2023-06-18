package com.assu.study.chap02;

import java.io.Serializable;
import java.util.Currency;

public final class Money implements Serializable {
  private final Long value;
  private final Currency currency;

  public Money(Long value, Currency currency) {
    if (value == null || value < 0) {
      throw new IllegalArgumentException("invalid value");
    }

    if (currency == null) {
      throw new IllegalArgumentException("invalid currency");
    }

    this.value = value;
    this.currency = currency;
  }

  public Long getValue() {
    return value;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Money money = (Money) o;
    return getValue().equals(money.getValue()) && getCurrency().equals(money.getCurrency());
  }
}
