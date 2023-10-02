package com.assu.study.chap07.service;

import com.assu.study.chap07.controller.HotelRoomResponse;
import com.assu.study.chap07.domain.HotelRoomEntity;
import com.assu.study.chap07.repository.HotelRoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class HotelRoomDisplayServiceTest02 {

  @Autowired  // 필드 주입 방식
  private HotelRoomDisplayService hotelRoomDisplayService;

  // Mock 객체 생성
  // 테스트 프레임워크가 만든 HotelRoomRepository Mock 객체가 ApplicationContext 에 포함되고,
  // 해당 Mock 객체는 ApplicationContext 로 HotelRoomDisplayService 에 주입됨
  @MockBean
  private HotelRoomRepository hotelRoomRepository;

  @Test
  void testGetHotelRoomById() {
    // Mock 객체의 행동 설정
    // findById() 메서드가 호출되면 아이디가 10L 인 HotelRoomEntity 객체 리턴
    // findById() 메서드에 '어떤 인자' 를 의미하는 any() 를 설정했으므로 인수값에 상관없이
    // willReturn() 메서드에 인자로 설정된 HotelRoomEntity 객체를 무조건 리턴
    given(this.hotelRoomRepository.findById(any()))
        .willReturn(new HotelRoomEntity(10L, "test", 1, 2, 3));


    HotelRoomResponse hotelRoomResponse = hotelRoomDisplayService.getHotelRoomById(1L);

    Assertions.assertNotNull(hotelRoomResponse);
    Assertions.assertEquals(10L, hotelRoomResponse.getId());
  }

  @Test
  void testWillAnswer() {
    given(this.hotelRoomRepository.findById(any()))
        // 스텁 메서드가 응답하는 클래스의 타입(HotelRoomEntity)을 제네릭으로 입력
        // 즉, hotelRoomRepository.findById() 메서드가 응답하는 클래스 타입은 HotelRoomEntity
        .willAnswer(new Answer<HotelRoomEntity>() {
          @Override
          // InvocationOnMock 은 Mock 메서드의 인자, 메서드명, Mock 객체 정보를 포함하여, 이 정보들을 참조할 수 있는 메서드 제공
          public HotelRoomEntity answer(InvocationOnMock invocationOnMock) throws Throwable {
            // 런타임 시점에서 Mock 메서드의 인자 정보 획득
            Long id = invocationOnMock.getArgument(0);
            if (id != null && id > 10) {
              return new HotelRoomEntity(id, "CODE", 3, 3, 3);
            } else {
              return new HotelRoomEntity(10L, "test", 1, 2, 3);
            }
          }
        });

    HotelRoomResponse hotelRoomResponse = hotelRoomDisplayService.getHotelRoomById(1L);

    Assertions.assertNotNull(hotelRoomResponse);
    Assertions.assertEquals(10L, hotelRoomResponse.getId());
  }
}