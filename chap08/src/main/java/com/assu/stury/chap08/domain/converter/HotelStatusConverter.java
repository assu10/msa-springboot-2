package com.assu.stury.chap08.domain.converter;

import com.assu.stury.chap08.domain.HotelStatus;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class HotelStatusConverter implements AttributeConverter<HotelStatus, Integer> {

  // 데이터베이스에서 사용할 데이터로 변환
  // HotelStatus 의 value 값을 리턴하는 getValue() 리턴
  @Override
  public Integer convertToDatabaseColumn(HotelStatus attribute) {
    if (Objects.isNull(attribute)) {
      return null;
    }
    return attribute.getValue();
  }

  // 데이터베이스에 저장된 값을 HotelStatus 열거형으로 변환
  // 데이터베이스에 저장된 Integer 값과 매핑되는 HotelStatus 상수를 리턴하는 fromValue() 리턴
  @Override
  public HotelStatus convertToEntityAttribute(Integer dbData) {
    if (Objects.isNull(dbData)) {
      return null;
    }
    return HotelStatus.fromValue(dbData);
  }
}
