package com.assu.study.chap09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriComponentsBuilderTest {

  @Test
  void testBuild() {
    // URI 를 리턴하는 build() 이므로 내부적으로 인코딩하는 케이스
    // given, when
    URI uri = UriComponentsBuilder.fromPath("/hotels/{hotelName}")
        .queryParam("type", "{type}")
        .queryParam("isActive", "{isActive}")
        .scheme("https").host("127.0.0.1").port(18080)
        .build("한국 hotel", "Hotel", "true");

    // then
    Assertions.assertEquals("https://127.0.0.1:18080/hotels/%ED%95%9C%EA%B5%AD%20hotel?type=Hotel&isActive=true", uri.toString());
  }

  @Test
  void testEncoding() {
    // 1. URI 를 리턴하는 build() 이므로 내부적으로 인코딩하는 케이스
    URI firstUri = UriComponentsBuilder.fromPath("/hotels/{hotelName}")
        .scheme("https").host("127.0.0.1").port(18080)
        .build("한국호텔"); // URL 경로에 포함된 템플릿 변수를 인자로 치환하고 인코딩함

    Assertions.assertEquals("https://127.0.0.1:18080/hotels/%ED%95%9C%EA%B5%AD%ED%98%B8%ED%85%94", firstUri.toString());


    // 2. UriComponents 를 리턴하는 build() 이므로 내부적으로 인코딩하지 않는 케이스
    String variable = "한국호텔";
    String path = "/hotels/" + variable;  // 템플릿 변수 없음

    // 템플릿을 사용하지 않았으므로 UriComponents 를 리턴하는 build() 사용 (내부에서 인코딩하지 않음)
    URI secondUri = UriComponentsBuilder.fromPath(path)
        .scheme("https").host("127.0.0.1").port(18080)
        .build()  // UriComponents 리턴
        .toUri(); // UriComponents 를 리턴하므로 toUri() 를 통해 URI 객체로 변환

    // 퍼센트 인코딩을 물론 UTF-8 문자셋 인코딩도 되지 않음
    Assertions.assertEquals("https://127.0.0.1:18080/hotels/한국호텔", secondUri.toString());


    // 3. UriComponents 를 리턴하는 build() 이므로 내부적으로 인코딩하지 않기 때문에 별도의 인코딩해주는 케이스
    String variable2 = "한국호텔";
    String path2 = "/hotels/" + variable2;  // 템플릿 변수 없음

    URI thirdUri = UriComponentsBuilder.fromPath(path2)
        .scheme("https").host("127.0.0.1").port(18080)
        .build(false) // 받아온 인자값이 인코딩 되어있는지 여부에 따라 true/false 입력
        .encode() // 인코딩
        .toUri();

    Assertions.assertEquals("https://127.0.0.1:18080/hotels/%ED%95%9C%EA%B5%AD%ED%98%B8%ED%85%94", thirdUri.toString());
  }
}
