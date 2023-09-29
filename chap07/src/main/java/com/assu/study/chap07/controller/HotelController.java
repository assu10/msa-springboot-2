package com.assu.study.chap07.controller;

import com.assu.study.chap07.service.HotelDisplayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HotelController {
  private final HotelDisplayService hotelDisplayService;

  public HotelController(HotelDisplayService hotelDisplayService) {
    this.hotelDisplayService = hotelDisplayService;
  }

  @PostMapping("/hotels/name")
  public ResponseEntity<List<HotelResponse>> getHotelByName(@RequestBody HotelRequest hotelRequest) {
    List<HotelResponse> hotelResponse = hotelDisplayService.getHotelsByName(hotelRequest);
    return ResponseEntity.ok(hotelResponse);
  }
}