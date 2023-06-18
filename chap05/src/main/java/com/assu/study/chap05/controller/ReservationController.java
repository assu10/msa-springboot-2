package com.assu.study.chap05.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ReservationController {
  @GetMapping("/hotels/{hotelId}/rooms/{roomNumber}/reservations")
  public List<Long> getReservationsByPaging(
          @PathVariable Long hotelId,
          @PathVariable String roomNumber,
          Pageable pageable // @RequestParam 이 없어도 Pageable 클래스를 인자로 선언하면 page, size, sort 파라메터값을 매핑한 Pageagle 객체를 주입함
  ) {
    System.out.println("page: " + pageable.getPageNumber());  // page 파라메터값 리턴
    System.out.println("size: " + pageable.getPageSize());  // size 파라메터값 리턴

    // Pageable 의 getSort() 는 sort 파라메터값과 대응하는 Sort 객체 리턴하고, sort 파라메터는 하나 이상의 값일 수 있음
    // 따라서 Sort 객체는 inner class 인 Sort.order 객체스트림을 구현함
    // stream() 을 사용하면 클라이언트가 전달한 파라메터 집합 처리 가능
    pageable.getSort().stream().forEach(order -> {
      System.out.println("sort: " + order.getProperty() + ", " + order.getDirection());
    });

    return Collections.emptyList();
  }
}
