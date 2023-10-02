package com.assu.study.chap07.controller;

import com.assu.study.chap07.domain.HotelRoomEntity;
import lombok.Getter;

// DTO
@Getter
public class HotelRoomResponse {
  private final Long id;
  private final String code;
  private final Integer floor;
  private final Integer bedCount;
  private final Integer bathCount;

  private HotelRoomResponse(Long id, String code, Integer floor, Integer bedCount, Integer bathCount) {
    this.id = id;
    this.code = code;
    this.floor = floor;
    this.bedCount = bedCount;
    this.bathCount = bathCount;
  }

  public static HotelRoomResponse from(HotelRoomEntity hotelRoomEntity) {
    return new HotelRoomResponse(hotelRoomEntity.getId(),
        hotelRoomEntity.getCode(),
        hotelRoomEntity.getFloor(),
        hotelRoomEntity.getBedCount(),
        hotelRoomEntity.getBathCount()
    );
  }
}
