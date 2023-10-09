package com.assu.stury.chap08.service;

import com.assu.stury.chap08.controller.HotelCreateRequest;
import com.assu.stury.chap08.controller.HotelCreateResponse;
import com.assu.stury.chap08.domain.HotelEntity;
import com.assu.stury.chap08.domain.HotelRoomEntity;
import com.assu.stury.chap08.repository.HotelRepository;
import com.assu.stury.chap08.repository.HotelRoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

// 16저1200
@SpringBootTest
//@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class HotelServiceTest {

  @Autowired
  private HotelService hotelService;

  @Autowired
  private HotelRepository hotelRepository;

  @Autowired
  private HotelRoomRepository hotelRoomRepository;

  @Test
  void createHotel() {
    // given
    HotelCreateRequest request = new HotelCreateRequest();
    request.setName("test");
    request.setAddress("test address");
    request.setPhoneNumber("555555");
    request.setRoomCount(10);

    // when
    HotelCreateResponse response = hotelService.createHotel(request);
    HotelEntity hotelEntity = hotelRepository.findById(response.getHotelId()).orElse(null);
    List<HotelRoomEntity> hotelRoomEntities = hotelRoomRepository.findByHotelId(response.getHotelId());

    //Then
    Assertions.assertNotNull(hotelEntity);
    Assertions.assertEquals(request.getName(), hotelEntity.getName());
    Assertions.assertEquals(request.getAddress(), hotelEntity.getAddress());
    Assertions.assertEquals(request.getPhoneNumber(), hotelEntity.getPhoneNumber());
    Assertions.assertEquals(request.getRoomCount(), hotelEntity.getRoomCount());

    Assertions.assertEquals(request.getRoomCount(), hotelRoomEntities.size());
  }
}