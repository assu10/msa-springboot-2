package com.assu.study.chap06.controller;

import com.assu.study.chap06.domain.HotelRoomNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsyncTest {

  @GetMapping(path = "/hotels/{hotelId}/rooms/{roomId}")
  public Callable<HotelRoomResponse> getHotelRoomByPeriod(
      ClientInfo clientInfo,
      @PathVariable Long hotelId,
      @PathVariable HotelRoomNumber roomNumber
  ) {
    Callable<HotelRoomResponse> response = () -> {
      return HotelRoomResponse.of(hotelId, String.valueOf(roomNumber.getRoomNumber()));
    };
    return response;
  }
}
