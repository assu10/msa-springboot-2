package com.assu.study.chap06.converter;

import com.assu.study.chap06.controller.HotelRoomType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// 문자열을 HotelRoomType enum 변환하는 컨버터 구현체
@Component
public class HotelRoomTypeConverter implements Converter<String, HotelRoomType> {
  @Override
  public HotelRoomType convert(String source) {
    return HotelRoomType.fromParam(source);
  }
}
