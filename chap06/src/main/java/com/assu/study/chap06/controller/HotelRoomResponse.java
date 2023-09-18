package com.assu.study.chap06.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

@Getter
public class HotelRoomResponse {

  @JsonProperty("id")
  @JsonSerialize(using = ToStringSerializer.class)
  private final Long hotelRoomId;

  private final String roomNumber;

  public HotelRoomResponse(Long hotelRoomId, String roomNumber) {
    this.hotelRoomId = hotelRoomId;
    this.roomNumber = roomNumber;
  }

  public static HotelRoomResponse of(Long hotelRoomId, String roomNumber) {
    return new HotelRoomResponse(hotelRoomId, roomNumber);
  }
}
