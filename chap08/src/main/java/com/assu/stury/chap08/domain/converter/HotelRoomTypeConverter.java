package com.assu.stury.chap08.domain.converter;

import com.assu.stury.chap08.domain.HotelRoomType;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

// @Component
public class HotelRoomTypeConverter implements AttributeConverter<HotelRoomType, Integer> {

  // 데이터베이스에서 사용할 데이터로 변환
  // HotelRoomType 의 value 값을 리턴하는 getValue() 리턴
  @Override
  public Integer convertToDatabaseColumn(HotelRoomType attribute) {
    if (Objects.isNull(attribute)) {
      return null;
    }

    return attribute.getValue();
  }

  // 데이터베이스에 저장된 값을 HotelRoomType 열거형으로 변환
  // 데이터베이스에 저장된 Integer 값과 매핑되는 HotelRoomType 상수를 리턴하는 fromValue() 리턴
  @Override
  public HotelRoomType convertToEntityAttribute(Integer dbData) {
    if (Objects.isNull(dbData)) {
      return null;
    }

    return HotelRoomType.fromValue(dbData);
  }
}
