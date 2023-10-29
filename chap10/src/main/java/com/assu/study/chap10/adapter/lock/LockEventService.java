package com.assu.study.chap10.adapter.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class LockEventService {
  //private LockAdapter lockAdapter;

//  @Transactional(timeout = 10)  // 트랜잭션의 타임아웃을 10초로 설정
//  public Boolean attendEvent(Long hotelId, Long userId) {
//
//    // 레디스에 분산락이 없는지 확인
//    // holdLock() 이 false 를 리턴하면 다른 인스턴스나 스레드로 락이 생성되었음을 의미
//    // 따라서 attendEvent() 도 false 를 리턴
//    if (!lockAdapter.holdLock(hotelId, userId)) {
//      return false;
//    }
//
//    EventHotelEntity eventHotelEntity = eventHotelRepository.findByHotelId(hotelId);
//
//    // DB 에서 hotelId 와 일치하는 엔티티 객체 조회
//    // nonEmptyUser() 는 엔티티 객체에 winnerUserId 의 null 여부 확인
//    // null 이 아니면 다른 사용자가 이미 선착순 이벤트에 성공한 것이므로 false 리턴
//    // 따라서 attendEvent() 도 false 를 리턴
//    if (eventHotelEntity.nonEmptyUser()) {
//      return false;
//    }
//
//    // 엔티티에 winnerUserId 를 설정하는 winner() 실행 후 DB 에 저장
//    eventHotelEntity.winner(userId);
//    eventHotelRepository.save(eventHotelEntity);
//
//    return true;
//  }
}
