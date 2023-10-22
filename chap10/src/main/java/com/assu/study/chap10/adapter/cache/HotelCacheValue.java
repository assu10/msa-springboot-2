package com.assu.study.chap10.adapter.cache;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

// VO
@Getter
@ToString
@EqualsAndHashCode
public final class HotelCacheValue {
  private final String name;
  private final String address;

  private HotelCacheValue(String name, String address) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name can't be null.");
    }
    if (Objects.isNull(address)) {
      throw new IllegalArgumentException("address can't be null.");
    }
    this.name = name;
    this.address = address;
  }

  @JsonCreator // 언마셜링 과정(JSON -> 객체)에서 값 변환에 사용되는 메서드를 지정
  public static HotelCacheValue of(@JsonProperty("name") String name,
                                   @JsonProperty("address") String address) {
    return new HotelCacheValue(name, address);
  }
}
