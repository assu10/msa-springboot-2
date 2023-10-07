package com.assu.stury.chap08.repository;

import com.assu.stury.chap08.domain.HotelEntity;
import com.assu.stury.chap08.domain.HotelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // HotelRepository 를 스프링 빈으로 등록하여 다른 스프링 빈 객체에 주입할 수 있도록 함
// JpaRepository<T, ID> 는 두 가지 제네릭 타입을 받음
// 첫 번째 인자는 영속할 대상인 엔티티 클래스이고, 두 번째 인자는 엔티티 클래스에 정의한 기본키의 클래스 타입
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
  List<HotelEntity> findByStatus(HotelStatus status);

  @Query(value = "SELECT h FROM hotels AS h WHERE h.hotelId = ?1 AND h.status = 0")
  HotelEntity findReadyOne(Long hotelId);

  @Query("SELECT h FROM hotels AS h WHERE h.hotelId = :hotelId AND h.status = :status")
  HotelEntity findOne(@Param("hotelId") Long hotelId, @Param("status") HotelStatus status);
}
