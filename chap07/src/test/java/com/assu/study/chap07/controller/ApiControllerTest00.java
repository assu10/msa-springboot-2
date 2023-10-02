package com.assu.study.chap07.controller;

import com.assu.study.chap07.util.JsonUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// MockMvcBuilders 클래스를 사용하여 MockMvc 객체 생성도 가능하지만, 스프링 부트에서 제공하는 @AutoConfigureMockMvc 를 정의하면 MockMvc 객체를 스프링 빈으로 주입받을 수 있음
// @SpringBootTest 애너테이션을 사용하여 스프링 MVC 기능 테스트 시 함께 사용함
@AutoConfigureMockMvc
// WebEnvironment.MOCK 을 설정하여 실제 서블릿 컨테이너를 실행하지 않고 Mock 서블릿 컨테이너를 사용하여 테스트 진행
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ApiControllerTest00 {

  // @AutoConfigureMockMvc 로 생성된 MockMvc 스프링 빈 주입받음
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetHotelByName() throws Exception {
    HotelRequest hotelRequest = new HotelRequest("Assu Hotel.");
    String jsonRequest = JsonUtil.objectMapper.writeValueAsString(hotelRequest);

    mockMvc.perform(post("/hotels/name")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk()) // 응답 메시지가 200 인지 검증
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string("[{\"hotelId\":1000,\"hotelName\":\"Assu Hotel~\",\"address\":\"Seoul Gangnam\",\"phoneNumber\":\"+821112222\"}]"))
        .andExpect(content().json("[{\"hotelId\":1000,\"hotelName\":\"Assu Hotel~\",\"address\":\"Seoul Gangnam\",\"phoneNumber\":\"+821112222\"}]"))
        .andExpect(jsonPath("$[0].hotelId", Matchers.is(1000)))
        .andExpect(jsonPath("$[0].hotelName", Matchers.is("Assu Hotel~")))
        .andDo(MockMvcResultHandlers.print(System.out));
  }
}