package com.assu.study.chap10.service;

import lombok.Getter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

@Getter
@ToString
@Cacheable
public class HotelRequest {
  private final Long hotelId;

  public HotelRequest(Long hotelId) {
    this.hotelId = hotelId;
  }
}
