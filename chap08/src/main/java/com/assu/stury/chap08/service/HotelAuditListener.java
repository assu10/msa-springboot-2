package com.assu.stury.chap08.service;

import com.assu.stury.chap08.domain.HotelEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotelAuditListener {
  // HotelEntity 객체가 생성된 후 발생하는 이벤트 처리
  // logWhenCreated() 메서드가 콜백 메서드
  // HotelEntity 객체가 생성되면 logWhenCreated() 메서드가 실행됨
  // 콜백 메서드의 인자는 이벤트가 발생한 HotelEntity 객체를 인자로 주입받을 수 있음
  @PostPersist
  public void logWhenCreated(HotelEntity hotelEntity) {
    log.warn("hotel created. id: {}", hotelEntity.getHotelId());
  }

  // 여러 애너테이션을 중복해서 사용할 수도 있음
  @PostUpdate
  @PostRemove
  public void logWhenChanged(HotelEntity hotelEntity) {
    log.warn("hotel changed. id: {}, name: {}", hotelEntity.getHotelId(), hotelEntity.getName());
  }
}
