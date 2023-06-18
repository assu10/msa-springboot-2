package com.assu.study.chap05.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HotelRoomIdResponse {
  @JsonProperty("id") // JSON 객체로 마셜링하는 과정에서 hotelRoomId 속성명 대신 다른 id 가 JSON 객체의 속성명이 됨
  @JsonSerialize(using = ToStringSerializer.class)  // 마셜링 과정에서 hotelRoomId 의 Long 타입을 String 타입으로 변경
  private Long hotelRoomId;

  private HotelRoomIdResponse(Long hotelRoomId) {
    if (Objects.isNull(hotelRoomId)) {
      throw new IllegalArgumentException("hotelRoomId is null.");
    }
    this.hotelRoomId = hotelRoomId;
  }

  public static HotelRoomIdResponse from(Long hotelRoomId) {
    return new HotelRoomIdResponse(hotelRoomId);
  }
}
