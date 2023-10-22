package com.assu.study.chap10.adapter.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CacheAdapter {
  private final RedisTemplate<HotelCacheKey, HotelCacheValue> hotelCacheRedisTemplate;
  private final ValueOperations<HotelCacheKey, HotelCacheValue> hotelCacheOperation;

  public CacheAdapter(RedisTemplate<HotelCacheKey, HotelCacheValue> hotelCacheRedisTemplate) {
    this.hotelCacheRedisTemplate = hotelCacheRedisTemplate;
    // CacheAdapter 클래스는 레디스의 key-value 자료 구조를 사용하므로 RedisTemplate 의 opsForValue() 를 사용하여 ValueOperations 객체 생성
    // ValueOperations 객체는 key-value 자료 구조에서 사용할 수 있는 get(), set(), delete() 와 같은 메서드들을 제공함
    this.hotelCacheOperation = hotelCacheRedisTemplate.opsForValue();
  }

  public void put(HotelCacheKey key, HotelCacheValue value) {
    // 유효 기간은 24시간으로 설정
    hotelCacheOperation.set(key, value, Duration.ofSeconds(24 * 60 * 60));
  }

  public HotelCacheValue get(HotelCacheKey key) {
    return hotelCacheOperation.get(key);
  }

  public void delete(HotelCacheKey key) {
    hotelCacheRedisTemplate.delete(key);
  }
}
