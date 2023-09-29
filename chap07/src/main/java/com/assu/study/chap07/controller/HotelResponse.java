package com.assu.study.chap07.controller;

import lombok.Getter;
import lombok.ToString;

// DTO
@ToString
@Getter
public class HotelResponse {
  private final Long hotelId;
  private final String hotelName;
  private final String address;
  private final String phoneNumber;

  public HotelResponse(Long hotelId, String hotelName, String address, String phoneNumber) {
    this.hotelId = hotelId;
    this.hotelName = hotelName;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }
}
