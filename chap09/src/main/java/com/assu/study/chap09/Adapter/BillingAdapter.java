package com.assu.study.chap09.Adapter;

import com.assu.study.chap09.controller.ApiResponse;
import com.assu.study.chap09.controller.BillingCodeResponse;
import com.assu.study.chap09.controller.CreateCodeRequest;
import com.assu.study.chap09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class BillingAdapter {

  private final RestTemplate restTemplate;

  public BillingAdapter(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public CreateCodeResponse createWithPostForEntity(List<Long> hotelIds) {
    URI uri = UriComponentsBuilder.fromPath("/billing-codes")
        .scheme("http").host("127.0.0.1").port(8080)
        .build(false).encode().toUri();

    CreateCodeRequest request = new CreateCodeRequest(1, hotelIds);

    ResponseEntity<ApiResponse> responseEntity = restTemplate.postForEntity(uri, request, ApiResponse.class);

    if (HttpStatus.OK != responseEntity.getStatusCode()) {
      log.error("Error from Billing. status:{}, hotelIds:{}", responseEntity.getStatusCode(), hotelIds);
      throw new RuntimeException("Error from Billing. " + responseEntity.getStatusCode());
    }

    ApiResponse apiResponse = responseEntity.getBody();

    // data 속성값을 Map 으로 캐스팅
    // JSON 객체의 data 속성값은 "codes": [111,222,333] 이므로 Map 의 key 는 "codes" 문자열이, Map 의 값에는 Long 타입의 리스트가 저장됨
    Map<String, List<Long>> dataMap = (Map) apiResponse.getData();

    return CreateCodeResponse.of(dataMap.get("codes"));
  }

  public List<BillingCodeResponse> getBillingCodes(String codeNameParam) {
    // URI 객체 생성
    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/billing-codes")
        .scheme("http").host("127.0.0.1").port(8080);

    if (Objects.nonNull(codeNameParam)) {
      builder.queryParam("codeName", codeNameParam);
    }

    URI uri = builder.build(false).encode().toUri();

    // GET 메서드를 이용하여 서버에 요청
    // 두 번째 인자를 응답 메시지를 변환할 클래스 타입
    // 두 번째 인자가 ApiResponse.class 이므로 리턴 타입은 ResponseEntity<ApiResponse> 가 됨
    ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(uri, ApiResponse.class);

    if (HttpStatus.OK != responseEntity.getStatusCode()) {
      log.error("Error from Billing. status: {}, param: {}", responseEntity.getStatusCode(), codeNameParam);
      throw new RuntimeException("Error From Billing~" + responseEntity.getStatusCode());
    }

    // getBody() 는 HTTP 응답 메시지의 바디값을 응답
    // 단, getForEntity() 메서드의 인자로 사용된 클래스 타입으로 응답하므로 ApiResponse 객체로 응답
    ApiResponse apiResponse = responseEntity.getBody();

    // 제네릭 타입이 설정되어 있지 않은 ApiResponse 객체의 data 속성의 클래스 타입은 Object 이므로
    // 적절한 타입의 객체로 변경하려면 타입 캐스팅 필요
    return (List<BillingCodeResponse>) apiResponse.getData();
  }
}
