package com.assu.study.chap10.config;

import com.assu.study.chap10.service.HotelKeyGenerator;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class BasicCacheConfig {

  @Bean
  public RedisConnectionFactory basicCacheRedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("127.0.0.1", 6379);

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

  // 스프링 빈의 이름은 cacheManager
  // 레디스 서버에 캐시 데이터 저장
  @Bean
  public CacheManager cacheManager() {
    // RedisCacheConfigurations 은 캐시 데이터를 저장하는 RedisCache 를 설정하는 기능 제공
    RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()  // defaultCacheConfig() 는 기본값으로 설정된 RedisCacheConfiguration 객체 리턴
        .entryTtl(Duration.ofHours(1))  // 캐시의 유효 기간을 1시간으로 설정
        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // 캐시 데이터 key 직렬화 시 문자열로 변환하여 저장
        //.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class)));  // 캐시 데이터 value 직렬화할 때 JSON 으로 변환하여 저장
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));  // 캐시 데이터 value 직렬화할 때 JSON 으로 변환하여 저장

    Map<String, RedisCacheConfiguration> configurations = new HashMap<>();
    // HashMap configuration 객체의 key 는 캐시 이름을 저장하고, value 는 캐시를 설정할 수 있는 RedisConfiguration 저장
    // hotelCache 캐시의 설정은 defaultConfig 설정과 같지만 캐시 데이터의 유효 기간을 30분으로 변경
    // RedisCacheConfiguration 은 불변 객체이므로 entryTtl() 은 새로운 RedisCacheConfiguration 객체를 리턴
    // 즉, 기존 defaultConfig 객체에는 영향이 없음
    configurations.put("hotelCache", defaultConfig.entryTtl(Duration.ofMinutes(30)));
    configurations.put("hotelAddressCache", defaultConfig.entryTtl(Duration.ofDays(1)));

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(basicCacheRedisConnectionFactory())  // RedisCacheManager 가 사용할 RedisConnectionFactory 객체 설정
        .cacheDefaults(defaultConfig) // RedisCacheConfiguration 인자를 사용하여 RedisCacheManager 의 기본 캐시 설정
        .withInitialCacheConfigurations(configurations) // RedisCacheManager 생성 시 초기값 설정, 따라서 RedisCacheManager 스프링 빈이 생성되면 hotelCache 와 hotelAddressCache 캐시 값이 설정됨
        .build();
  }

  @Bean
  public HotelKeyGenerator hotelKeyGenerator() {
    return new HotelKeyGenerator();
  }
}
