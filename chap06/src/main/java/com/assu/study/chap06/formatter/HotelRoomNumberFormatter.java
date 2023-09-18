package com.assu.study.chap06.formatter;

import com.assu.study.chap06.domain.HotelRoomNumber;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

// HotelRoomNumberConverter 처럼 roomNumber 문자열을 HotelRoomNumber 객체로 변환하는 포매터
public class HotelRoomNumberFormatter implements Formatter<HotelRoomNumber> {
  @Override
  public HotelRoomNumber parse(String text, Locale locale) throws ParseException {
    return HotelRoomNumber.parse(text);
  }

  @Override
  public String print(HotelRoomNumber object, Locale locale) {
    return object.toString();
  }
}
