package com.assu.study.chap09.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Component
public class WebClientConfig {
  @Bean
  public WebClient webClient() {
    // tcpConfiguration() 는 deprecated 됨
//    HttpClient httpClient = HttpClient.create()
//        .tcpConfiguration(tcpClient ->
//            tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // 커넥션을 생성하는 최대 시간
//                .doOnConnected(conn ->
//                    conn.addHandlerLast(new ReadTimeoutHandler(10)) // 서버의 응답 메시지를 읽는데 걸리는 최대 시간 (s)
//                        .addHandlerLast(new WriteTimeoutHandler(10))  // 서버에 데이터를 전송할 때 걸리는 최대 시간 (s)
//                )
//        );
    HttpClient httpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // 커넥션을 생성하는 최대 시간
        .doOnConnected(conn ->
            conn.addHandlerLast(new ReadTimeoutHandler(10))   // 서버의 응답 메시지를 읽는데 걸리는 최대 시간 (s)
                .addHandlerLast(new WriteTimeoutHandler(10))  // 서버에 데이터를 전송할 때 걸리는 최대 시간 (s)
        );

    // 생성한 HttpClient 객체를 하용하여 ClientHttpConnector 객체 생성
    // wiretap() 메서드를 사용하여 요청 메시지와 응답 메시지 전체를 로깅할 수 있음
    // 단, 로그 설정 파일에 logging.level.reactor.netty.http.client=DEBUG 레벨로 설정해야 함
    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));

    return WebClient.builder()
        .baseUrl("http://localhost:8080") // REST-API 의 기본 URL
        .clientConnector(connector)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .build();
  }
}
