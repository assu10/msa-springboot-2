package com.assu.study.chap10.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelServiceTest {

  @Autowired
  private HotelService hotelService;

  @Test
  void getHotelNameAndAddress() {
    // key: hotelCache::testHotelName
    // value: "{\"@class\":\"com.assu.study.chap10.service.HotelResponse\",\"hotelId\":1111,\"hotelName\":\"testHotelName\",\"hotelAddress\":\"testHotelAddress\"}"
    hotelService.getHotelNameAndAddress("testHotelName2", "testHotelAddress");
    hotelService.getHotelNameAndAddress("testHotelName2", "t");
  }


  @Test
  void getHotel() {
    // key: hotelCache::HOTEL::2222
    // value: "{\"@class\":\"com.assu.study.chap10.service.HotelResponse\",\"hotelId\":2222,\"hotelName\":\"ASSU2\",\"hotelAddress\":\"seoul2\"}"
    HotelRequest request = new HotelRequest(2222L);
    hotelService.getHotel(request);
  }
}