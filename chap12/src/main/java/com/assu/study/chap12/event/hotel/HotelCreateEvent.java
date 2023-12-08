package com.assu.study.chap12.event.hotel;

import lombok.Getter;
import lombok.ToString;

// 이벤트 메시지 클래스
// 불변(immutable) 클래스 이어야 하므로 Setter 는 없음
@Getter
@ToString
public final class HotelCreateEvent {
    private final Long hotelId;
    private final String hotelAddr;

    private HotelCreateEvent(Long hotelId, String hotelAddr) {
        this.hotelId = hotelId;
        this.hotelAddr = hotelAddr;
    }

    // 정적 팩토리 메서드 패턴
    public static HotelCreateEvent of(Long hotelId, String hotelAddr) {
        return new HotelCreateEvent(hotelId, hotelAddr);
    }
}
