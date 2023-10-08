package com.assu.stury.chap08.repository;

import com.assu.stury.chap08.domain.HotelRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoomEntity, Long> {

  List<HotelRoomEntity> findByHotelId(Long hotelId);
}
