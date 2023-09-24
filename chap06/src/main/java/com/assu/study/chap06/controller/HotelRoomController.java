package com.assu.study.chap06.controller;

import com.assu.study.chap06.domain.HotelRoomNumber;
import com.assu.study.chap06.domain.KafkaProperties;
import com.assu.study.chap06.util.IdGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@RestController
public class HotelRoomController {

  KafkaProperties kafkaProperties;

  public HotelRoomController(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  @GetMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
  public HotelRoomResponse getHotelRoomByPeriod(
      ClientInfo clientInfo,
      @PathVariable Long hotelId,
      @PathVariable HotelRoomNumber roomNumber,
      @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate fromDate,
      @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate toDate
  ) {

    //System.out.println("--------" + clientInfo);
    System.out.println("--------" + kafkaProperties.getAckLevel());
    System.out.println("--------" + kafkaProperties.getTopic().getCheckout());
    System.out.println("--------" + kafkaProperties.getTopic().getReservation());
    System.out.println("--------" + kafkaProperties.getBootstrapServers());

    Long hotelRoomId = IdGenerator.create();
    BigDecimal originalPrice = new BigDecimal("130.00");

    HotelRoomResponse response = HotelRoomResponse.of(hotelRoomId, String.valueOf(roomNumber.getRoomNumber()), HotelRoomType.DOUBLE, originalPrice);

    if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
      fromDate.datesUntil(toDate.plusDays(1)).forEach(date -> response.reservedAt(date));
    }
    return response;
  }
}
