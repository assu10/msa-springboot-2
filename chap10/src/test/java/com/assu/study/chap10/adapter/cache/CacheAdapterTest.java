package com.assu.study.chap10.adapter.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CacheAdapterTest {

  @Autowired
  private CacheAdapter cacheAdapter;

  @Test
  @DisplayName("Cache 에서 데이터 조회")
  void get() {
    // given
    HotelCacheKey key = HotelCacheKey.from(1111L);
    HotelCacheValue value = HotelCacheValue.of("assu", "seoul");
    cacheAdapter.put(key, value);

    // when
    HotelCacheValue result = cacheAdapter.get(key);
    log.info("result: {}", result);

    // then
    Assertions.assertEquals(value, result);
  }

  @Test
  @DisplayName("Cache 에서 데이터 삭제")
  void delete() {
    // given
    HotelCacheKey key = HotelCacheKey.from(1111L);
    HotelCacheValue value = HotelCacheValue.of("assu", "seoul");
    cacheAdapter.put(key, value);

    // when
    cacheAdapter.delete(key);

    // then
    HotelCacheValue result = cacheAdapter.get(key);
    Assertions.assertNull(result);
  }
}