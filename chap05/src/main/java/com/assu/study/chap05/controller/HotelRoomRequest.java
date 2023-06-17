package com.assu.study.chap05.controller;

import com.assu.study.chap05.domain.HotelRoomType;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

// DTO
@Getter
@ToString
public class HotelRoomRequest {
  private String roomNumber;
  private HotelRoomType roomType;
  private BigDecimal originalPrice;
}
