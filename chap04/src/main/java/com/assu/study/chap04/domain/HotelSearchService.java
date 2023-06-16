package com.assu.study.chap04.domain;

import org.springframework.stereotype.Service;

@Service
public class HotelSearchService {
  public Hotel getHotelById(Long hotelId) {
    return new Hotel(hotelId, "ASSU Hotel", "Seoul", 100);
  }
}
