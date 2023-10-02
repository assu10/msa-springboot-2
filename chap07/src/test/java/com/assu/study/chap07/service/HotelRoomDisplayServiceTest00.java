package com.assu.study.chap07.service;

import com.assu.study.chap07.controller.HotelRoomResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelRoomDisplayServiceTest00 {

  @Autowired  // 필드 주입 방식
  private HotelRoomDisplayService hotelRoomDisplayService;

  @Test
  void testOriginalGetHotelRoomById() {
    HotelRoomResponse hotelRoomResponse = hotelRoomDisplayService.getHotelRoomById(1L);

    Assertions.assertNotNull(hotelRoomResponse);
    Assertions.assertEquals(1L, hotelRoomResponse.getId());
  }
}