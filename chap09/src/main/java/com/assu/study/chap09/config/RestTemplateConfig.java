package com.assu.study.chap09.config;

import com.assu.study.chap09.server.IdentityHeaderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public ClientHttpRequestFactory clientHttpRequestFactory() {
    // RestTemplate 의 ClientHttpRequestFactory 구현체로 SimpleClientHttpRequestFactory 사용
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

    // 클라이언트와 서버 사이에 커넥션 객체 생성 시 소요되는 최대 시간 (ms)
    factory.setConnectTimeout(3000);

    // 클라이언트가 서버에 데이터 처리를 요청하고 응답받기까지 소요되는 최대 시간 (ms)
    factory.setReadTimeout(1000);

    // SimpleClientHttpRequestFactory 는 요청 메시지의 바디를 버퍼링하는 기능을 제공함
    // 디폴트는 true
    // 요청 메시지의 바디를 버퍼링하지 않으려면 false 로 설정
    factory.setBufferRequestBody(false);

    return factory;
  }

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
    // ClientHttpRequestFactory 인자를 받는 생성자를 사용하여 RestTemplate 객체 생성
    // 바로 위에서 설정한 ClientHttpRequestFactory 스프링 빈을 주입받아서 RestTemplate 객체 생성
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    // RestTemplate 객체에 새로운 인터셉터 객체 추가
    // getInterceptors() 는 RestTemplate 객체에 설정된 인터셉터 리스트 객체를 리턴하므로 리스트 객체의 add() 를 사용하여
    // IdentityHeaderInterceptor 객체 추가
    restTemplate.getInterceptors().add(new IdentityHeaderInterceptor());

    // RestTemplate 객체에 새로운 ResponseErrorHandler 설정
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

    return restTemplate;
  }
}
