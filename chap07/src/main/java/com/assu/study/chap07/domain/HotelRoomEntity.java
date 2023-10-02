package com.assu.study.chap07.domain;

import lombok.Getter;
import lombok.ToString;

// DTO
@Getter
@ToString
public class HotelRoomEntity {
  private final Long id;
  private final String code;
  private final Integer floor;
  private final Integer bedCount;
  private final Integer bathCount;

  public HotelRoomEntity(Long id, String code, Integer floor, Integer bedCount, Integer bathCount) {
    this.id = id;
    this.code = code;
    this.floor = floor;
    this.bedCount = bedCount;
    this.bathCount = bathCount;
  }
}
