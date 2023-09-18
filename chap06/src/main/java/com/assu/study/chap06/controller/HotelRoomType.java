package com.assu.study.chap06.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelRoomType {
  SINGLE("single"), // 내부 속성인 param 값는 single
  DOUBLE("double"),
  TRIPLE("triple"),
  QUAD("quad");

  private final String param; // 마셜링 후 사용되는 JSON 객체값 저장

  // 모든 enum 상수 선언 시 JSON 객체 값으로 사용될 값을 인수로 입력
  // SINGLE 상수는 문자열 'single' 이 param 값으로 할당됨
  HotelRoomType(String param) {
    this.param = param;
  }

  private static final Map<String, HotelRoomType> paramMap = Arrays.stream(HotelRoomType.values())
      .collect(Collectors.toMap(
          HotelRoomType::getParam,
          Function.identity()
      ));


  // param 인자를 받아서 paramMap 의 key 와 일치하는 enum 상수를 리턴
  // 언마셜링 과정에서 JSON 속성값이 "single" 이면 fromParam() 메서드가 HotelRoomType.SINGLE 로 변환함
  @JsonCreator  // 언마셜링 과정(JSON -> 객체)에서 값 변환에 사용되는 메서드를 지정
  public static HotelRoomType fromParam(String param) {
    return Optional.ofNullable(param) // 값이 존재하면 값을 감싸는 Optional 반환, null 이면 빈 Optional 반환
        .map(paramMap::get)
        .orElseThrow(() -> new IllegalArgumentException("param is not valid."));  // 값이 존재하면 값 반환, 없으면 Supplier 에서 생성한 예외 발생
  }

  // enum 상수에 정의된 param 변수값 리턴 (= getParam() 이 리턴하는 문자열이 JSON 속성값으로 사용됨)
  // 마셜링 과정에서 HotelRoomType.SINGLE 이 "single" 로 변환됨
  @JsonValue  // 마셜링 과정(객체 -> JSON)에서 값 변환에 사용되는 메서드 지정, 이 애너테이션이 있으면 마셜링 때 toString() 이 아닌 getParam() 사용
  public String getParam() {
    return this.param;
  }
}

// param 인자를 받아서 paramMap 의 key 와 일치하는 enum 상수를 리턴
// 언마셜링 과정에서 JSON 속성값이 "single" 이면 fromParam() 메서드가 HotelRoomType.SINGLE 로 변환함
//  @JsonCreator  // 언마셜링 과정(JSON -> 객체)에서 값 변환에 사용되는 메서드를 지정
//  public static HotelRoomType fromParam(String param) {
//    return Optional.ofNullable(param)
//        .map(paramMap::get)
//        .orElseThrow(() -> new IllegalArgumentException("param is not valid."));
//  }

// enum 상수에 정의된 param 변수값 리턴 (= getParam() 이 리턴하는 문자열이 JSON 속성값으로 사용됨)
// 마셜링 과정에서 HotelRoomType.SINGLE 이 "single" 로 변환됨
//  @JsonValue  // 마셜링 과정(객체 -> JSON)에서 값 변환에 사용되는 메서드 지정, 이 애너테이션이 있으면 마셜링 때 toString() 이 아닌 getParam() 사용
//  public String getParam() {
//    return this.param;
//  }