package com.assu.study.chap09.adapter;

import com.assu.study.chap09.controller.ApiResponse;
import com.assu.study.chap09.controller.CreateCodeRequest;
import com.assu.study.chap09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class WebClientBillingAdapter {
  private static final ParameterizedTypeReference<ApiResponse<CreateCodeResponse>> TYPE_REFERENCE;

  static {
    TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };
  }

  private final WebClient webClient;

  public WebClientBillingAdapter(WebClient webClient) {
    this.webClient = webClient;
  }

  public CreateCodeResponse createWithWebClient(List<Long> hotelIds) {
    URI uri = UriComponentsBuilder.fromPath("/billing-codes")
        .scheme("http").host("127.0.0.1").port(8080)
        .build(false).encode().toUri();

    CreateCodeRequest request = new CreateCodeRequest(1, hotelIds);

    return webClient.mutate().build()   // mutate() 메서드로 WebClient.Builder 를 다시 사용할 수 있음을 보여줌, 설정 후 build() 메서드를 다시 호출하면 다시 WebClient 객체 리턴받음
        .method(HttpMethod.POST).uri(uri)   // 기존에 WebClientConfig 에서 설정한 baseUrl() 설정이 있더라도 덮어씀
        .bodyValue(request) // HTTP 요청 메시지의 바디 부분 설정
        .retrieve()   // 서버의 REST-API 실행
        .onStatus(httpStatus -> HttpStatus.OK != httpStatus,  // HTTP 의 상태 코드를 이용하여 에러 처리
            response -> Mono.error(new RuntimeException("Error from Billing. " + response.statusCode().value())))
        .bodyToMono(TYPE_REFERENCE) // ParameterizedTypeReference 상수를 사용하여 바디를 Mono 로 응답
        .flux().toStream()    // 리턴받은 Mono 를 Flux 로 변환한 후 다시 Stream 객체로 변환
        .findFirst()
        .map(ApiResponse::getData)
        .orElseThrow(() -> new RuntimeException("Empty response"));
  }
}
