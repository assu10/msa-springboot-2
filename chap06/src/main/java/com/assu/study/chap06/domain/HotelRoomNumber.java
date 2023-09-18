package com.assu.study.chap06.domain;

import lombok.Getter;

import java.util.StringJoiner;

@Getter
public class HotelRoomNumber {
  private static final String DELIMITER = "-";

  private final String buildingCode;
  private final Long roomNumber;

  public HotelRoomNumber(String buildingCode, Long roomNumber) {
    this.buildingCode = buildingCode;
    this.roomNumber = roomNumber;
  }

  // roomNumber (ocean-5678) 을 파싱하여 HotelRoomNumber 객체 리턴
  public static final HotelRoomNumber parse(String roomNumberId) {
    String[] tokens = roomNumberId.split(DELIMITER);
    if (tokens.length != 2) {
      throw new IllegalArgumentException("invalid roomNumberId format.");
    }
    return new HotelRoomNumber(tokens[0], Long.parseLong(tokens[1]));
  }

  @Override
  public String toString() {
    return new StringJoiner(DELIMITER)
        .add(buildingCode)
        .add(roomNumber.toString())
        .toString();
  }
}
