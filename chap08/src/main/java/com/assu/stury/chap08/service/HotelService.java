package com.assu.stury.chap08.service;

import com.assu.stury.chap08.controller.HotelCreateRequest;
import com.assu.stury.chap08.controller.HotelCreateResponse;
import com.assu.stury.chap08.domain.HotelEntity;
import com.assu.stury.chap08.domain.HotelRoomEntity;
import com.assu.stury.chap08.domain.HotelRoomType;
import com.assu.stury.chap08.repository.HotelRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HotelService {
//  @Autowired
//  private HotelService hotelService;

  @Autowired
  private ApplicationContext applicationContext;

  private HotelService self;

  @PostConstruct
  private void init() {
    self = applicationContext.getBean(HotelService.class);
  }

  private final HotelRepository hotelRepository;

  public HotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  // 메서드 내부에 데이터를 생성하는 save() 가 있으므로 readOnly 를 false 로 설정 (디폴트라 안해도 되긴 함)
  @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
  public HotelCreateResponse createHotel(HotelCreateRequest createRequest) {
    HotelEntity hotelEntity = HotelEntity.of(
        createRequest.getName(),
        createRequest.getAddress(),
        createRequest.getPhoneNumber()
    );

    int roomCount = createRequest.getRoomCount();
    List<HotelRoomEntity> hotelRoomEntitis = IntStream.range(0, roomCount)  // IntStream 반환
        .mapToObj(i -> HotelRoomEntity.of("ROOM-" + i, HotelRoomType.DOUBLE, BigDecimal.valueOf(100)))  // Stream<HotelRoomEntity> 반환
        .collect(Collectors.toList());

    hotelEntity.addHotelRooms(hotelRoomEntitis);

    hotelRepository.save(hotelEntity);
    return HotelCreateResponse.of(hotelEntity.getHotelId());
  }

  // this 키워드를 사용하여 @Transactional 애너테이션이 걸려 있는 내부 메서드 호출하는 예시
  public List<HotelCreateResponse> createHotels(List<HotelCreateRequest> createRequests) {
//    return createRequests.stream()
//        .map(createRequest -> this.createHotel(createRequest))
//        .collect(Collectors.toList());
    return createRequests.stream()
        .map(createRequest -> self.createHotel(createRequest))
        .collect(Collectors.toList());
  }
}
