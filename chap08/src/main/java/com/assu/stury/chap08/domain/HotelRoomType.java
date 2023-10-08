package com.assu.stury.chap08.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelRoomType {
  SINGLE("single", 0),  // HotelRoomType.SINGLE 열거형 상수의 내부 속성인 param 값은 'single' 문자열
  DOUBLE("double", 1),
  TRIPLE("triple", 2),
  QUAD("quad", 3);

  private final String param;
  private final Integer value;

  // Map 의 key 는 HotelRoomType 열거형의 param 로, Map 의 value 는 HotelRoomType 의 상수로 저장
  // 빠르게 변환하고자 static 구문으로 클래스가 로딩될 때 Map 객체를 미리 생성함
  private static final Map<String, HotelRoomType> parmaMap = Arrays.stream(HotelRoomType.values())
      .collect(Collectors.toMap(
          HotelRoomType::getParam,
          Function.identity())
      );

  private static final Map<Integer, HotelRoomType> valueMap = Arrays.stream(HotelRoomType.values())
      .collect(Collectors.toMap(
          HotelRoomType::getValue,
          Function.identity())
      );

  // 모든 enum 상수 선언 시 JSON 객체 값으로 사용될 값을 인수로 입력
  // SINGLE 상수는 문자열 'single' 이 param 값으로 할당, 0 이 value 값으로 할당
  HotelRoomType(String param, Integer value) {
    this.param = param;
    this.value = value;
  }

  // PARAM 인자를 받아서 paramMap 의 key 와 일치하는 enum 상수 리턴
  // 언마셜링(JSON -> 객체) 과정에서 JSON 속성값이 single 이면 fromValue 메서드가 HotelRoomType.SINGLE 로 변환
  @JsonCreator  // 언마셜링 과정에서 값 변환에 사용되는 메서드 지정
  public static HotelRoomType fromParam(String param) {
    return Optional.ofNullable(param) // Optional<String> 반환
        .map(parmaMap::get) // Optional<HotelRoomType> 반환
        .orElseThrow(() -> new IllegalArgumentException("param is not valid."));
  }

  public static HotelRoomType fromValue(Integer value) {
    return Optional.ofNullable(value) // Optional<Integer> 반환
        .map(valueMap::get) // Optional<HotelRoomType> 반환
        .orElseThrow(() -> new IllegalArgumentException("value is not valid"));
  }

  // enum 상수에 정의된 param 변수값 리턴 (= getParam() 이 리턴하는 문자열이 JSON 속성값으로 사용됨)
  // 마셜링 (객체 -> JSON) 과정에서 HotelRoomType.SINGLE 이 "single" 로 변환됨
  @JsonValue  // 마셜링 과정에서 값 변환에 사용되는 메서드 지정, 이 애너테이션이 있으면 마셜링 때 toString() 이 아닌 getParam() 사용
  public String getParam() {
    return param;
  }

  public Integer getValue() {
    return value;
  }
}
