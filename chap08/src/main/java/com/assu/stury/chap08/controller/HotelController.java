package com.assu.stury.chap08.controller;

import com.assu.stury.chap08.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
  private final HotelService hotelService;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("createHotel")
  public void createHotel() {
    HotelCreateRequest request = new HotelCreateRequest();
    request.setName("test");
    request.setAddress("test address");
    request.setPhoneNumber("555555");
    request.setRoomCount(10);

    // when
    HotelCreateResponse response = hotelService.createHotel(request);
  }
}
