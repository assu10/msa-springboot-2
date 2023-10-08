package com.assu.stury.chap08.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelCreateResponse {
  private Long hotelId;

  public static HotelCreateResponse of(Long hotelId) {
    HotelCreateResponse response = new HotelCreateResponse();
    response.hotelId = hotelId;
    return response;
  }
}
