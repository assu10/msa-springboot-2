package com.assu.study.chap07.controller;

import lombok.Getter;
import lombok.ToString;

// DTO
@Getter
@ToString
public class HotelRequest {
  private String hotelName;

  public HotelRequest() {
  }

  public HotelRequest(String hotelName) {
    this.hotelName = hotelName;
  }
}
