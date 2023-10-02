package com.assu.study.chap07.service;

import com.assu.study.chap07.config.ThreadPoolConfigTest;
import com.assu.study.chap07.controller.HotelRoomResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
// @Import 나 @ContextConfiguration 을 사용하여 명시적으로 테스트 자바 설정 클래스 로딩
//@Import(value = {ThreadPoolConfigTest.class})
@ContextConfiguration(classes = ThreadPoolConfigTest.class)
// 테스트 환경에 맞게 커스터마이징된 프로퍼티 파일 로딩 (application-test.properties 에는 스프링 빈을 재정의할 수 있는 설정값을 포함하고 있음)
@TestPropertySource(locations = "classpath:application-test.properties")
class HotelRoomDisplayServiceTest01 {

  @Autowired  // 필드 주입 방식
  private HotelRoomDisplayService hotelRoomDisplayService;

  @Test
  void testGetHotelRoomById() {
    HotelRoomResponse hotelRoomResponse = hotelRoomDisplayService.getHotelRoomById(1L);

    Assertions.assertNotNull(hotelRoomResponse);
    Assertions.assertEquals(1L, hotelRoomResponse.getId());
  }
}