package com.assu.study.chap04.controller;

import com.assu.study.chap04.domain.Hotel;
import com.assu.study.chap04.domain.HotelSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // DispatcherServlet 이 전달하는 사용자 요청을 받는 클래스
public class ApiController {
  private HotelSearchService hotelSearchService;

  public ApiController(HotelSearchService hotelSearchService) {
    this.hotelSearchService = hotelSearchService;
  }

  @ResponseBody // ResponseEntity<Hotel> 객체를 JSON 형식으로 변경
  @RequestMapping(method = RequestMethod.GET, path = "/hotels/{hotelId}")
  public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") Long hotelId) {
    Hotel hotel = hotelSearchService.getHotelById(hotelId);
    return ResponseEntity.ok(hotel);
  }
}
