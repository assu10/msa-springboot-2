package com.assu.study.chap06.controller;

import com.assu.study.chap06.controller.serializer.ToDollarStringSerializer;
import com.assu.study.chap06.util.IdGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// DTO
@Getter
public class HotelRoomResponse {

  @JsonProperty("id") // JSON 객체로 마셜링하는 과정에서 hotelRoomId 속성명 대신 id 가 JSON 객체의 속성명이 됨
  @JsonSerialize(using = ToStringSerializer.class)  // 마셜링 과정에서 hotelRoomId 의 Long 타입을 String 타입으로 변경
  private final Long hotelRoomId;
  private final String roomNumber;
  private final HotelRoomType hotelRoomType;  // hotelRoomType 은 열거형 클래스로 enum 상수도 JSON 형식으로 마셜링됨

  @JsonSerialize(using = ToDollarStringSerializer.class)
  // 마셜링 과정 (객체 -> JSON) 에서 BigDecimal 타입을 달러타입으로 변경하기 위해 커스텀 구현체로 지정
  private final BigDecimal originalPrice;
  private final List<Reservation> reservations;


  public HotelRoomResponse(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
    this.hotelRoomId = hotelRoomId;
    this.roomNumber = roomNumber;
    this.hotelRoomType = hotelRoomType;
    this.originalPrice = originalPrice;
    reservations = new ArrayList<>();
  }

  // 호출하는 쪽에서 생성자 직접 호출하지 않게 하기 위해..
  // 정적 팩토리 메서드 패턴
  public static HotelRoomResponse of(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
    return new HotelRoomResponse(hotelRoomId, roomNumber, hotelRoomType, originalPrice);
  }

  public void reservedAt(LocalDate reservedAt) {
    reservations.add(new Reservation(IdGenerator.create(), reservedAt));
  }

  @Getter // Reservation 객체로 마셜링 되어야하므로 @Getter 애너테이션 정의
  private static class Reservation {
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final Long reservationId;

    // 마셜링 과정에서 LocalDate 타입의 데이터를 String 타입의 사용자 정의 포맷으로 변경하기 위해 @JsonFormat 애너테이션 사용
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate reservedDate;

    public Reservation(Long reservationId, LocalDate reservedDate) {
      this.reservationId = reservationId;
      this.reservedDate = reservedDate;
    }
  }
}
