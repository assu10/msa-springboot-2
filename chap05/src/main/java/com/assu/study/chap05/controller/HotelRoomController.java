package com.assu.study.chap05.controller;

import com.assu.study.chap05.domain.HotelRoomType;
import com.assu.study.chap05.utils.IdGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@RestController // @Controller 와 @ResponseBody 애너테이션의 기능을 동시에 제공
                // HomeRoomController 가 리턴하는 객체들은 JSON 메시지 형태로 마셜링됨
public class HotelRoomController {

  @GetMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
  public HotelRoomResponse getHotelRoomByPeriod(
          @PathVariable(value="hotelId") Long hotelId,
          @PathVariable String roomNumber,  // value 생략 가능
          @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd")LocalDate fromDate,
          @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate toDate) {

    Long hotelRoodId = IdGenerator.create();
    BigDecimal originalPrice = new BigDecimal("100.00");

    HotelRoomResponse response = HotelRoomResponse.of(hotelRoodId, roomNumber, HotelRoomType.DOUBLE, originalPrice);

    if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
      fromDate.datesUntil(toDate.plusDays(1))
              .forEach(date -> response.reservedAt(date));
    }
    return response;
  }

  @DeleteMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
  public DeleteResultResponse deleteHotelRoom(
          @PathVariable Long hotelId,
          @PathVariable String roomNumber
  ) {
    System.out.println("deleted!");
    return new DeleteResultResponse(Boolean.TRUE, "success");
  }
}
