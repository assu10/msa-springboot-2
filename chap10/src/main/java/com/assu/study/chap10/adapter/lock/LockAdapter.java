package com.assu.study.chap10.adapter.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class LockAdapter {
  private final RedisTemplate<LockKey, Long> lockRedisTemplate;
  private final ValueOperations<LockKey, Long> lockOperation;

  public LockAdapter(RedisTemplate<LockKey, Long> lockRedisTemplate) {
    this.lockRedisTemplate = lockRedisTemplate;
    this.lockOperation = lockRedisTemplate.opsForValue();
  }

  // 레디스에 락 생성
  // hotelId 를 사용하여 LockKey 객체를 생성하고, 레디스 key 에 저장
  // 이 때 value 는 userId
  public Boolean holdLock(Long hotelId, Long userId) {
    LockKey lockKey = LockKey.from(hotelId);
    // setIfAbsent() 는 레디스 key 와 매핑되는 값이 없을때만 레디스 데이터 생성
    // 데이터가 없으면 Boolean.TRUE 리턴
    // 즉, Boolean.FALSE 를 리턴하면 레디스에 이미 데이터가 있음을 의미하므로 분산 락이 있다는 의미이기 때문에
    // 공유 자원에 작업하지 않음
    // 레디스의 유효 기간은 10초로 설정
    return lockOperation.setIfAbsent(lockKey, userId, Duration.ofSeconds(10));
  }

  // 레디스에 락이 있는지 확인
  public Long checkLock(Long hotelId) {
    LockKey lockKey = LockKey.from(hotelId);
    return lockOperation.get(lockKey);
  }

  // 레디스에서 락을 삭제
  public void clearLock(Long hotelId) {
    lockRedisTemplate.delete(LockKey.from(hotelId));
  }
}
