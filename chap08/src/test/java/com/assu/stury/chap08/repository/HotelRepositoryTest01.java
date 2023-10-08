package com.assu.stury.chap08.repository;

import com.assu.stury.chap08.domain.HotelEntity;
import com.assu.stury.chap08.domain.HotelStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;


@DataJpaTest
//@Transactional  // @DataJpaTest 에 @Transactional 이 이미 포함되어 있음
@TestPropertySource(locations = "classpath:application-test-h2.properties")
class HotelRepositoryTest01 {

  private static HotelEntity testHotelEntity;

  // 테스트 대상 클래스인 HotelRepository 스프링 빈을 주입받음
  @Autowired
  private HotelRepository hotelRepository;

  // 모든 테스트 케이스들을 실행할때마다 초기값 설정
  @BeforeEach
  void setUp() {
    testHotelEntity = HotelEntity.of("ASSU HOTEL", "SEOUL", "+8201011112222");
  }

  @Test
  void testFindByStatus() {
    // Given (테스트 케이스를 준비하는 과정 (=어떤 상황이 주어졌을 때), 객체 초기화)
    hotelRepository.save(testHotelEntity);

    // When (테스트 실행 (= 대상 코드가 동작한다면))
    HotelEntity hotelEntity = hotelRepository.findByStatus(HotelStatus.READY)
        .stream()
        .filter(entity -> entity.getHotelId().equals(testHotelEntity.getHotelId()))
        .findFirst()
        .get();

    // Then (테스트를 검증 (= 기대한 값과 수행 결과가 맞는지))
    Assertions.assertNotNull(hotelEntity);
    Assertions.assertEquals(testHotelEntity.getAddress(), hotelEntity.getAddress());
    Assertions.assertEquals(testHotelEntity.getName(), hotelEntity.getName());
    Assertions.assertEquals(testHotelEntity.getPhoneNumber(), hotelEntity.getPhoneNumber());
  }
}