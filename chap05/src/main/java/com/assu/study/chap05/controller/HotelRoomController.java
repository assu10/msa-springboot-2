package com.assu.study.chap05.controller;

import com.assu.study.chap05.domain.HotelRoomType;
import com.assu.study.chap05.utils.IdGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController // @Controller 와 @ResponseBody 애너테이션의 기능을 동시에 제공
                // HomeRoomController 가 리턴하는 객체들은 JSON 메시지 형태로 마셜링됨
public class HotelRoomController {

  private static final String HEADER_CREATED_AT = "X-CREATED-AT";
  private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

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

  @PostMapping(path = "/hotels/{hotelId}/rooms")
  public ResponseEntity<HotelRoomIdResponse> createHotelRoom(
          @PathVariable Long hotelId,
          @RequestBody HotelRoomRequest hotelRoomRequest  // @RequestBody 는 클라이언트에서 전송한 요청 메시지의 body 를 언마셜링하여 자바 객체인 HotelRoomRequest 로 변환
  ) {
    System.out.println(hotelRoomRequest.toString());

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HEADER_CREATED_AT, DATE_FORMATTER.format(ZonedDateTime.now()));
    HotelRoomIdResponse body = HotelRoomIdResponse.from(1_002_003L);

    return new ResponseEntity<>(body, headers, HttpStatus.OK);
  }
}
