package com.assu.study.chap07.repository;

import com.assu.study.chap07.domain.HotelRoomEntity;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRoomRepository {
  public HotelRoomEntity findById(Long id) {
    return new HotelRoomEntity(id, "EAST-111", 9, 2, 1);
  }
}
