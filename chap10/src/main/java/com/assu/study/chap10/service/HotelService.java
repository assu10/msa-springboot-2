package com.assu.study.chap10.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelService {

  // CacheManager 의 hotelCache 캐시에 저장됨
  // hotelCache 에 정의된 설정값에 따라 캐시 데이터 관리 (BasicCacheConfig 클래스 참고)
  // BasicCacheConfig 에서 hotelCache 캐시의 TTL 은 30분으로 설정함
  // 미리 설정된 캐시가 없다면 CacheManager 의 기본 설정에 따라 캐시를 생성하고 데이터 저장
  @Cacheable(value = "hotelCache")  // 캐시 이름을 hotelCache 로 설정
  // 리턴되는 HotelResponse 객체를 사용하여 캐시 데이터 생성
  public HotelResponse getHotelById(Long hotelId) { // Long HotelId 메서드 인자의 toString() 을 사용하여 캐시 key 생성
    return new HotelResponse(hotelId, "ASSU Hotel", "seoul");
  }

  @Cacheable(
      value = "hotelCache",
      key = "#hotelName",
      condition = "#hotelName > '' && #hotelAddress.length() > 2"
  )
  public HotelResponse getHotelNameAndAddress(String hotelName, String hotelAddress) {
    log.info("-----");
    return new HotelResponse(1111L, hotelName, hotelAddress);
  }

  @Cacheable(
      value = "hotelCache",
      keyGenerator = "hotelKeyGenerator"
  )
  public HotelResponse getHotel(HotelRequest hotelRequest) {
    return new HotelResponse(hotelRequest.getHotelId(), "ASSU2", "seoul2");
  }
}
