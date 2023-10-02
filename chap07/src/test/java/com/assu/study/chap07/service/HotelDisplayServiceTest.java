package com.assu.study.chap07.service;

import com.assu.study.chap07.controller.HotelRequest;
import com.assu.study.chap07.controller.HotelResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest // 스프링 프레임워크의 기능과 함께 대상 클래스 테스트, Junit4는 @Runwith(SpringRunner.class) 설정 필요, Junit5 는 @Runwith 생략 가능
//@SpringBootTest(properties = {"search.host=127.0.0.1", "search.port=99999"})
//@SpringBootTest(properties = {"spring.config.location=classpath:application-test.properties"})
public class HotelDisplayServiceTest {

  private final HotelDisplayService hotelDisplayService;
  private final ApplicationContext applicationContext;

  // 테스트 클래스에 테스트 대상 스프링 빈을 생성자 주입 방식으로 사용 시 반드시 생성자에 @Autowired 정의해야 함
  // (필드 주입 방식을 사용하여 스프링 빈 주입도 가능하긴 함)
  @Autowired
  public HotelDisplayServiceTest(HotelDisplayService hotelDisplayService, ApplicationContext applicationContext) {
    this.hotelDisplayService = hotelDisplayService;

    // 스프링 프레임워크에서 제공하는 모든 스프링 빈을 주입받아 사용할 수 있음
    this.applicationContext = applicationContext;
  }

  @Test
  public void testReturnOneHotelWhenRequestIsHotelName() {
    // Given
    HotelRequest hotelRequest = new HotelRequest("Assu Hotel");

    // When
    // 주입받은 hotelDisplayService 스프링 빈 객체의 메서드를 테스트할 수 있음
    List<HotelResponse> hotelResponses = hotelDisplayService.getHotelsByName(hotelRequest);

    // Then
    Assertions.assertNotNull(hotelResponses);
    Assertions.assertEquals(1, hotelResponses.size());
  }

  @Test
  public void testApplicationContext() {
    // 주입받은 applicationContext 의 getBean() 메서드를 사용하여 DisplayService 타입의 스프링 빈을 받아옴
    DisplayService displayService = applicationContext.getBean(DisplayService.class);

    Assertions.assertNotNull(displayService);
    Assertions.assertTrue(displayService instanceof HotelDisplayService);
  }
}
