package com.assu.study.chap07.service;

import com.assu.study.chap07.aspect.ElapseLoggable;
import com.assu.study.chap07.controller.HotelRequest;
import com.assu.study.chap07.controller.HotelResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class HotelDisplayService implements DisplayService {
  @Override
  @ElapseLoggable
  // 메서드 처리 시간을 로깅하기 위해 @ElapseLoggable 애너테이션 정의, 해당 애너테이션의 @Target 설정은 ElementType.METHOD 이므로 메서드에만 사용 가능
  public List<HotelResponse> getHotelsByName(HotelRequest hotRequest) {

    //  throw new RuntimeException("test exception");

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      log.error("error!", e);
    }

    return List.of(
        new HotelResponse(1_000L, "Assu Hotel~", "Seoul Gangnam", "+821112222")
    );
  }
}