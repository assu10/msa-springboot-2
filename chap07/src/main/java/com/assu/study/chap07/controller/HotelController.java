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
  //private final HotelRoomDisplayService hotelRoomDisplayService;

//  public HotelController(HotelDisplayService hotelDisplayService, HotelRoomDisplayService hotelRoomDisplayService) {
//    this.hotelDisplayService = hotelDisplayService;
//    this.hotelRoomDisplayService = hotelRoomDisplayService;
//  }


  public HotelController(HotelDisplayService hotelDisplayService) {
    this.hotelDisplayService = hotelDisplayService;
  }

  @PostMapping("/hotels/name")
  // List<HotelResponse> 객체를 리턴하며, @RestController 가 JSON 으로 변경함, List 객체이므로 마셜링되면 JSON Array 형태로 변환됨
  public ResponseEntity<List<HotelResponse>> getHotelByName(
      @RequestBody HotelRequest hotelRequest) { // POST 요청의 Content-type 헤더값은 application/json 이고, Body 는 JSON 형식의 데이터
    List<HotelResponse> hotelResponse = hotelDisplayService.getHotelsByName(hotelRequest);
    return ResponseEntity.ok(hotelResponse);
  }

//  @GetMapping("/hotel/room/{id}")
//  public void getHotelRoomById(@PathVariable Long id) {
//    hotelRoomDisplayService.getHotelRoomById(id);
//  }
}