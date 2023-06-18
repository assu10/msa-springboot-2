package com.assu.study.chap05.domain.reservation;

import com.assu.study.chap05.domain.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReserveService {
  public Long reserveHotelRoom(Long hotelId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
    if (1==1) {
      // 해당 메서드를 호출하는 곳에 @ExceptionHandler 가 선언된 예외 처리 메서드가 있으면
      // BadRequestException 예외 객체를 예외 처리 메서드로 전달
      Optional.empty().orElseThrow(() -> {
        return new BadRequestException("BadRequestException!!!");
      });

    }
    return 1_002_003L;
  }
}
