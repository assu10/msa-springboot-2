package com.assu.study.chap10.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

// RedisConnectionFactory 스프링 빈 생성
@Slf4j
@Configuration
public class CacheConfig {
  // RedisStandaloneConfiguration 으로 RedisConnectionFactory 스프링 빈 생성
//  @Bean
//  public RedisConnectionFactory cacheRedisConnectionFactory() {
//    // 레디스 서버의 IP, 포트 설정
//    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
//
//    // 레디스의 데이터베이스 번호 설정
//    // 레디스 서버는 내부에서 16개의 데이터베이스를 구분해서 운영 가능하며, 0~15번까지의 데이터베이스를 가짐
//    // 개발 환경에서 장비를 효츌적으로 사용하기 위해 데이터베이스를 구분하여 각 컴포넌트에 할당해서 운영 가능
//    configuration.setDatabase(0);
//    configuration.setUsername("username");
//    configuration.setPassword("password");
//
//    // 레디스와 클라이언트 사이에 커넥션을 생성할 때 소요되는 최대 시간 설정
//    // 설정한 SocketOptions 객체는 ClientOptions 객체에 다시 랩핑함
//    // 랩핑한 ClientOptions 는 LettuceClientConfigurationBuilder 의 clientOptions() 메서드를 사용하여 설정 가능
//    final SocketOptions socketOptions = SocketOptions.builder().connectTimeout(Duration.ofSeconds(10)).build();
//    final ClientOptions clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();
//
//    LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
//        .clientOptions(clientOptions)
//        .commandTimeout(Duration.ofSeconds(5))  // 레디스 명령어를 실행하고 응답받는 시간 설정
//        .shutdownTimeout(Duration.ZERO) // 레디스 클라이언트가 안전하게 종료하려고 애플리케이션이 종료될 때까지 기다리는 최대 시간
//        .build();
//
//    return new LettuceConnectionFactory(configuration, lettuceClientConfiguration);
//  }

  // RedisSentinelConfiguration 으로 RedisConnectionFactory 스프링 빈 생성
//  @Bean
//  public RedisConnectionFactory cacheConnectionFactory() {
//    RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
//    // 레디스 센티넬이 모니터링할 레디스 서버 중 마스터 서버 이름 설정
//    configuration.setMaster("REDIS_MASTER_NAME");
//
//    // 레디스 센티넬 서버들 설정
//    configuration.sentinel("127.0.0.1", 1111);
//    configuration.sentinel("127.0.0.1", 1112);
//    configuration.sentinel("127.0.0.1", 1113);
//
//    // 센티넬 암호 설정
//    configuration.setPassword("password");
//
//    return new LettuceConnectionFactory(configuration);
//  }

  // RedisClusterConfiguration 으로 RedisConnectionFactory 스프링 빈 생성
  @Bean
  public RedisConnectionFactory cacheConnectionFactory() {
    RedisClusterConfiguration configuration = new RedisClusterConfiguration();

    // (error) Moved Redirection 처리 횟수 설정
    configuration.setMaxRedirects(3);

    // 레디스 클러스터에 포함된 레디스 서버 설정
    // 클러스터에 포함된 일부 노드 정보만 입력해도 클러스터의 모든 정보가 클라이언트에 동기화되므로 일부만 입력해도 사용 가능함
    configuration.setClusterNodes(List.of(
        new RedisNode("127.0.0.1", 1111),
        new RedisNode("127.0.0.1", 1112),
        new RedisNode("127.0.0.1", 1113)
    ));

    return new LettuceConnectionFactory(configuration);
  }
}
