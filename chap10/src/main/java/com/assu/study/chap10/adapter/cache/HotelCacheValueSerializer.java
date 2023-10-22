package com.assu.study.chap10.adapter.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class HotelCacheValueSerializer implements RedisSerializer<HotelCacheValue> {
  // JSON Mapper
  // HotelCacheValue 객체를 직렬화한 메시지 포맷은 JSON 메시지임
  // JSON 메시지 변환에 사용할 ObjectMapper 객체 생성
  // ObjectMapper 는 생성 비용이 비싸고, 멀티 스레드 환경에 안전하므로 static 변수로 생성하여 공요하는 형태로 사용함
  public static final ObjectMapper MAPPER = new ObjectMapper();
  private final Charset UTF_8 = StandardCharsets.UTF_8;

  @Override
  public byte[] serialize(HotelCacheValue hotelCacheValue) throws SerializationException {
    if (Objects.isNull(hotelCacheValue)) {
      return null;
    }

    try {
      // HotelCacheValue 객체를 JSON 메시지 문자열로 변경
      String json = MAPPER.writeValueAsString(hotelCacheValue);
      return json.getBytes(UTF_8);
    } catch (JsonProcessingException e) {
      throw new SerializationException("json serialize error", e);
    }
  }

  @Override
  public HotelCacheValue deserialize(byte[] bytes) throws SerializationException {
    // 레디스의 value 를 역직렬화할 때 null 검사 시 주의
    // 레디스 key 와 맞는 value 가 레디스에 없을수도 있으므로 null 인 경우 무조건 예외를 던지면 안됨
    if (Objects.isNull(bytes)) {
      return null;
    }

    try {
      // HotelCacheValue 객체로 변환
      return MAPPER.readValue(new String(bytes, UTF_8), HotelCacheValue.class);
    } catch (JsonProcessingException e) {
      throw new SerializationException("json deserialize error", e);
    }
  }
}
