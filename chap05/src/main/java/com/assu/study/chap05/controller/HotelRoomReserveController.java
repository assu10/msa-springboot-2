package com.assu.study.chap05.controller;

import com.assu.study.chap05.controller.validator.HotelRoomReserveValidator;
import com.assu.study.chap05.domain.reservation.ReserveService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelRoomReserveController {
  private final ReserveService reserveService;

  public HotelRoomReserveController(ReserveService reserveService) {
    this.reserveService = reserveService;
  }

  @InitBinder // initBinder() 초기화 함수에 선언
  void initBinder(WebDataBinder binder) {
    // 개발자가 Validator 를 확장한 클래스 추가
    binder.addValidators(new HotelRoomReserveValidator());
  }

  @PostMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}/reserve")
  public ResponseEntity<HotelRoomIdResponse> reserveHotelRoomByRoomNumber(
          @PathVariable Long hotelId,
          @PathVariable String roomNumber,
          @Valid @RequestBody HotelRoomReserveRequest reserveRequest,
          BindingResult bindingResult
  ) {
    // 예외 처리
    if (bindingResult.hasErrors()) {
      FieldError fieldError = bindingResult.getFieldError();
      String errorMessage = new StringBuilder(bindingResult.getFieldError().getCode())
              .append(" [").append(fieldError.getField()).append("] ")
              .append(fieldError.getDefaultMessage())
              .toString();

      System.out.println("error  : " + errorMessage);
      return ResponseEntity.badRequest().build();
    }

    System.out.println(reserveRequest.toString());

    Long reservationId = reserveService.reserveHotelRoom(
            hotelId,
            roomNumber,
            reserveRequest.getCheckInDate(),
            reserveRequest.getCheckOutDate()
    );

    HotelRoomIdResponse body = HotelRoomIdResponse.from(reservationId);

    return ResponseEntity.ok(body);
  }
}
