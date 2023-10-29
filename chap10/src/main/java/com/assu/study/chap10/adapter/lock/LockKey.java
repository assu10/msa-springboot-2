package com.assu.study.chap10.adapter.lock;

import java.util.Objects;

/**
 * 분산 락을 사용하기 위한 레디스 키 디자인 클래스
 */
public class LockKey {
  private static final String PREFIX = "LOCK::";
  private final Long eventHotelId;

  public LockKey(Long eventHotelId) {
    if (Objects.isNull(eventHotelId)) {
      throw new IllegalArgumentException("eventHotelId can't be null.");
    }
    this.eventHotelId = eventHotelId;
  }

  public static LockKey from(Long eventHotelId) {
    return new LockKey(eventHotelId);
  }

  // 레디스에 저장된 키를 LockKey 객체로 역직렬화할 때 사용
  public static LockKey fromString(String key) {
    String idToken = key.substring(0, PREFIX.length());
    Long eventHotelId = Long.valueOf(idToken);

    return LockKey.from(eventHotelId);
  }

  // LockKey 객체를 레디스의 키로 저장할 때 직렬화 과정에서 사용할 메서드
  // LOCK::eventHotelId 문자열 포맷으로 직렬화되어 저장됨
  @Override
  public String toString() {
    return PREFIX + eventHotelId;
  }
}
