package com.assu.study.chap05.controller.validator;

import com.assu.study.chap05.controller.HotelRoomReserveRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class HotelRoomReserveValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    // 검증 대상이 HotelRoomReserveRequest 이므로 검증 대상 클래스 타입인 clazz 인자와 HotelRoomReserveRequest 클래스가 같아야 함
    return HotelRoomReserveRequest.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    // target 인수를 HotelRoomReserveRequest 객체로 캐스팅
    // support() 로 확인한 객체만 validate() 의 target 인수로 넘어옴
    HotelRoomReserveRequest request = HotelRoomReserveRequest.class.cast(target);

    if (Objects.isNull(request.getCheckInDate())) {
      errors.rejectValue("checkInDate", "NotNull", "checkInDate is null");
      return;
    }
    if (Objects.isNull(request.getCheckOutDate())) {
      errors.rejectValue("checkOutDate", "NotNull", "checkOutDate is null");
      return;
    }
    // checkInDate 가 checkOutDate 보다 크면 검증 실패 입력
    if (request.getCheckInDate().compareTo(request.getCheckOutDate()) >= 0) {
      errors.rejectValue("checkOutDate", "Constraint Error", "checkOutDate is earlier than checkInDate");
      return;
    }
  }
}
