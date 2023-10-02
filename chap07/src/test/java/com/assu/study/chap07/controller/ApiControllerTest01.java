package com.assu.study.chap07.controller;

import com.assu.study.chap07.service.HotelDisplayService;
import com.assu.study.chap07.util.JsonUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest 의 controllers 속성을 사용하지 않으면 애플리케이션에 포함된 모든 컨트롤러 클래스 스캔
@WebMvcTest(controllers = HotelController.class)
public class ApiControllerTest01 {
  @Autowired
  private MockMvc mockMvc;

  // Mock 객체 생성
  @MockBean
  private HotelDisplayService hotelDisplayService;

  // Mockito 의 given() 과 willAnswer() 를 사용하여 적절한 값을 응답하도록 설정
  @BeforeEach
  public void init() {
    given(hotelDisplayService.getHotelsByName(any()))
        .willAnswer(new Answer<List<HotelResponse>>() {
          @Override
          public List<HotelResponse> answer(InvocationOnMock invocationOnMock) throws Throwable {
            HotelRequest hotelRequest = invocationOnMock.getArgument(0);
            return List.of(new HotelResponse(1L, hotelRequest.getHotelName(), "unknown", "111-222-3333"));
          }
        });
  }

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
        //.andExpect(content().string("[{\"hotelId\":1000,\"hotelName\":\"Assu Hotel~\",\"address\":\"Seoul Gangnam\",\"phoneNumber\":\"+821112222\"}]"))
        //.andExpect(content().json("[{\"hotelId\":1000,\"hotelName\":\"Assu Hotel~\",\"address\":\"Seoul Gangnam\",\"phoneNumber\":\"+821112222\"}]"))
        .andExpect(jsonPath("$[0].hotelId", Matchers.is(1)))
        .andExpect(jsonPath("$[0].hotelName", Matchers.is("Assu Hotel.")))
        .andDo(MockMvcResultHandlers.print(System.out));
  }

}
