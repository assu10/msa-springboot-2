package com.assu.study.chap05.controller;

import com.assu.study.chap05.domain.HotelRoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class HotelRoomUpdateRequest {
  @NotNull(message = "room Type can't be null")
  private HotelRoomType roomType;

  @NotNull(message = "originalPrice can't be null")
  @Min(value = 0, message = "originalPrice must be larger then 0")  // 0 보다 크거나 같아야 함
  private BigDecimal originalPrice;
}
