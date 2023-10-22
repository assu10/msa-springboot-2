package com.assu.study.chap10.config;

import com.assu.study.chap10.adapter.cache.HotelCacheKey;
import com.assu.study.chap10.adapter.cache.HotelCacheKeySerializer;
import com.assu.study.chap10.adapter.cache.HotelCacheValue;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

// RedisConnectionFactory 스프링 빈 생성
@Slf4j
@Configuration
public class CacheConfig {
  // RedisStandaloneConfiguration 으로 RedisConnectionFactory 스프링 빈 생성
  @Bean
  public RedisConnectionFactory cacheRedisConnectionFactory() {
    // 레디스 서버의 IP, 포트 설정
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("127.0.0.1", 6379);

    // 레디스의 데이터베이스 번호 설정
    // 레디스 서버는 내부에서 16개의 데이터베이스를 구분해서 운영 가능하며, 0~15번까지의 데이터베이스를 가짐
    // 개발 환경에서 장비를 효츌적으로 사용하기 위해 데이터베이스를 구분하여 각 컴포넌트에 할당해서 운영 가능
//    configuration.setDatabase(0);
//    configuration.setUsername("username");
//    configuration.setPassword("password");

    // 레디스와 클라이언트 사이에 커넥션을 생성할 때 소요되는 최대 시간 설정
    // 설정한 SocketOptions 객체는 ClientOptions 객체에 다시 랩핑함
    // 랩핑한 ClientOptions 는 LettuceClientConfigurationBuilder 의 clientOptions() 메서드를 사용하여 설정 가능
    final SocketOptions socketOptions = SocketOptions.builder().connectTimeout(Duration.ofSeconds(10)).build();
    final ClientOptions clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();

    LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
        .clientOptions(clientOptions)
        .commandTimeout(Duration.ofSeconds(5))  // 레디스 명령어를 실행하고 응답받는 시간 설정
        .shutdownTimeout(Duration.ZERO) // 레디스 클라이언트가 안전하게 종료하려고 애플리케이션이 종료될 때까지 기다리는 최대 시간
        .build();

    return new LettuceConnectionFactory(configuration, lettuceClientConfiguration);
  }

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
//  @Bean
//  public RedisConnectionFactory cacheConnectionFactory() {
//    RedisClusterConfiguration configuration = new RedisClusterConfiguration();
//
//    // (error) Moved Redirection 처리 횟수 설정
//    configuration.setMaxRedirects(3);
//
//    // 레디스 클러스터에 포함된 레디스 서버 설정
//    // 클러스터에 포함된 일부 노드 정보만 입력해도 클러스터의 모든 정보가 클라이언트에 동기화되므로 일부만 입력해도 사용 가능함
//    configuration.setClusterNodes(List.of(
//        new RedisNode("127.0.0.1", 1111),
//        new RedisNode("127.0.0.1", 1112),
//        new RedisNode("127.0.0.1", 1113)
//    ));
//
//    return new LettuceConnectionFactory(configuration);
//  }

  @Bean(name = "hotelCacheRedisTemplate")
  public RedisTemplate<HotelCacheKey, HotelCacheValue> hotelCacheRedisTemplate() {
    // RedisTemplate 는 제네릭 타입 K,V 설정 가능
    // 첫 번째는 레디스 key 에 해당하고, 두 번째는 레디스 value 에 해당함
    RedisTemplate<HotelCacheKey, HotelCacheValue> hotelCacheRedisTemplate = new RedisTemplate();

    // 위에서 생성한 RedisConnectionFactory 스프링 빈을 사용하여 RedisTemplate 객체 생성
    hotelCacheRedisTemplate.setConnectionFactory(cacheRedisConnectionFactory());

    // key 와 value 값을 직렬화/역직렬화하는 RedisSerializer 구현체 설정
//    hotelCacheRedisTemplate.setKeySerializer(new HotelCacheKeySerializer());
//    hotelCacheRedisTemplate.setValueSerializer(new HotelCacheValueSerializer());

    // Hash 자료 구조 사용 시 레디스 key, hash filed, hash value 의 RedisSerializer 3개 설정 필요
    hotelCacheRedisTemplate.setKeySerializer(new HotelCacheKeySerializer());
    hotelCacheRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
    hotelCacheRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<HotelCacheValue>(HotelCacheValue.class));

    return hotelCacheRedisTemplate;
  }
}
