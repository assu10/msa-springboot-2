package com.assu.study.chap09.config;

import org.apache.hc.client5.http.HttpRoute;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class PoolingRestTemplateConfig {
  @Bean
  public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
    PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
    manager.setMaxTotal(100); // 커넥션 풀에서 관리할 수 있는 총 커넥션 갯수, 여기서는 100개 설정
    manager.setDefaultMaxPerRoute(5); // 커넥션 풀에서 루트마다 관리할 수 있는 총 커넥션 갯수, 일반적으로 별도의 설정이 없다면 5개로 사용함

    // 터넥션 풀에서 특정 루트마다 관리할 수 있는 총 커넥션 개수 설정
    // defaultMaxPerRoute 설정을 덮어씀
    // 여기서는 http://10.192.10.111:8080 의 최대 커넥션 갯수는 10개로 설정
    HttpHost httpHost = new HttpHost("http", "10.192.10.111", 8080);
    manager.setMaxPerRoute(new HttpRoute(httpHost), 10);

    return manager;
  }

  @Bean
  public RequestConfig requestConfig() {
    return RequestConfig.custom()
        .setConnectionRequestTimeout(3000, TimeUnit.MILLISECONDS) // PoolingHttpClientConnectionManager 에서 커넥션을 요청해서 받기까지 걸리는 시간
        .setConnectTimeout(3000, TimeUnit.MILLISECONDS) // 서버와 클라이언트 사이의 커넥션을 생성하는 시간
        .setResponseTimeout(1000, TimeUnit.MILLISECONDS)  // HTTP 요청 메시지 전달 후 응답 메시지를 받기까지의 시간
        .build();
  }

  // 적절한 값으로 설정한 `PoolingHttpClientConnectionManager` 객체를 `CloseableHttpClient` 의 `setConnectionManager()` 메서드의 인자로 입력하면
  // `CloseableHttpClient` 는 `PoolingHttpClientConnectionManager` 에서 커넥션 객체를 받아 HTTP 통신을 함.
  @Bean
  public CloseableHttpClient httpClient() {
    // HttpClientBuilder 를 사용하여 CloseableHttpClient 객체 생성
    // 위에서 생성한 PoolingHttpClientConnectionManager 스프링 빈과 RequestConfig 스프링 빈을 주입받아 CloseableHttpClient 스프링 빈 생성
    return HttpClientBuilder.create()
        .setConnectionManager(poolingHttpClientConnectionManager())
        .setDefaultRequestConfig(requestConfig())
        .build();
  }

  // 커넥션 풀을 사용하는 CloseableHttpClient 를 사용하고자 HttpComponentsClientHttpRequestFactory 객체를 생성
  // 그리고 이를 사용하는 RestTemplate 스프링 빈 생성
  @Bean
  public RestTemplate poolingRestTemplate() {
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(httpClient());
    return new RestTemplate(requestFactory);
  }
}
