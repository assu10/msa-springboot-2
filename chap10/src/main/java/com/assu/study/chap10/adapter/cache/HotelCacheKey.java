package com.assu.study.chap10.adapter.cache;

import java.util.Objects;

public class HotelCacheKey {
  private static final String PREFIX = "HOTEL::";

  private final Long hotelId;

  private HotelCacheKey(Long hotelId) {
    if (Objects.isNull(hotelId)) {
      throw new IllegalArgumentException("hotelId can't be null.");
    }
    this.hotelId = hotelId;
  }

  // 정적 팩토리 메서드
  public static HotelCacheKey from(Long hotelId) {
    return new HotelCacheKey(hotelId);
  }

  public static HotelCacheKey fromString(String key) {
    String idToken = key.substring(0, PREFIX.length());
    Long hotelId = Long.valueOf(idToken);

    return HotelCacheKey.from(hotelId);
  }

  @Override
  public String toString() {
    return PREFIX +
        hotelId;
  }
}
