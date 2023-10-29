package com.assu.study.chap10.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class HotelResponse {
  private Long hotelId;
  private String hotelName;
  private String hotelAddress;
}