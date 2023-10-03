package com.assu.stury.chap08.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelStatus {
  // 내부에 Integer value 속성이 있음
  // HotelStatus 의 value 는 데이터베이스의 값으로 변환할 때 사용하는 열거형의 속성임
  OPEN(1), CLOSED(-1), READY(0);

  // Map 의 key 는 HotelStatus 열거형의 value 로, Map 의 value 는 HotelStatus 의 상수로 저장
  // 빠르게 변환하고자 static 구문으로 클래스가 로딩될 때 Map 객체를 미리 생성함
  private static final Map<Integer, HotelStatus> valueMap = Arrays.stream(HotelStatus.values())
      .collect(Collectors.toMap(HotelStatus::getValue, Function.identity()));
  private final Integer value;

  HotelStatus(Integer value) {
    this.value = value;
  }

  // 테이블에 저장된 value 값을 사용하여 열거형 상수로 변경할 때 사용할 메서드
  // value 와 매칭되는 열거형 상수를 valueMap 에서 받아와 리턴함
  public static HotelStatus fromValue(Integer value) {
    if (value == null) {
      throw new IllegalArgumentException("value is null");
    }
    return valueMap.get(value);
  }

  public Integer getValue() {
    return value;
  }
}
