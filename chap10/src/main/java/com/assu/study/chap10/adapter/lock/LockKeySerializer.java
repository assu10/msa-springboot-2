package com.assu.study.chap10.adapter.lock;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class LockKeySerializer implements RedisSerializer<LockKey> {
  private final Charset UTF_8 = StandardCharsets.UTF_8;

  @Override
  public byte[] serialize(LockKey lockKey) throws SerializationException {
    // 레디스 데이터 중 key 는 null 이 될 수 없음
    if (Objects.isNull(lockKey)) {
      throw new SerializationException("lockKey is null.");
    }
    // HotelCacheKey 가 직렬화되면 byte[] 를 리턴해야 함
    // 이 때 Charset 를 설정하여 byte[] 로 변환하는 것이 좋음
    return lockKey.toString().getBytes(UTF_8);
  }

  @Override
  public LockKey deserialize(byte[] bytes) throws SerializationException {
    if (Objects.isNull(bytes)) {
      throw new SerializationException("bytes is null.");
    }
    // 레디스의 key 데이터는 byte[] 이므로 적절히 변환하여 HotelCacheKey 객체를 생성하여 리턴
    return LockKey.fromString(new String(bytes, UTF_8));
  }
}
