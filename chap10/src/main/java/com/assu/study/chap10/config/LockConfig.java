package com.assu.study.chap10.config;

import com.assu.study.chap10.adapter.lock.LockKey;
import com.assu.study.chap10.adapter.lock.LockKeySerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class LockConfig {

  @Bean
  public RedisConnectionFactory lockRedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("127.0.0.1", 6379);

    // 레디스의 데이터베이스 번호 설정
    // 레디스 서버는 내부에서 16개의 데이터베이스를 구분해서 운영 가능하며, 0~15번까지의 데이터베이스를 가짐
    // 개발 환경에서 장비를 효츌적으로 사용하기 위해 데이터베이스를 구분하여 각 컴포넌트에 할당해서 운영 가능
//    configuration.setDatabase(0);
//    configuration.setUsername("username");
//    configuration.setPassword("password");

    return new LettuceConnectionFactory(configuration);
  }

  // redisTemplate 가 2개 (hotelCacheRedisTemplate, lockRedisTemplate) 인 경우 하나는
  // 디폴트 이름인 redisTemplate 로 해주어야 하나 봄 (lockRedisTemplate 가 아닌)
  @Bean(name = "redisTemplate")
  public RedisTemplate<LockKey, Long> lockRedisTemplate() {
    RedisTemplate<LockKey, Long> lockRedisTemplate = new RedisTemplate<>();

    // 위에서 생성한 RedisConnectionFactory 스프링 빈을 사용하여 RedisTemplate 객체 생성
    lockRedisTemplate.setConnectionFactory(lockRedisConnectionFactory());
    // key 와 value 값을 직렬화/역직렬화하는 RedisSerializer 구현체 설정
    lockRedisTemplate.setKeySerializer(new LockKeySerializer());
    lockRedisTemplate.setValueSerializer(new GenericToStringSerializer<>(Long.class));

    return lockRedisTemplate;
  }
}
