package com.assu.stury.chap08.domain;

import com.assu.stury.chap08.domain.converter.HotelRoomTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

//@EqualsAndHashCode
// @ToString
@Getter
@Entity(name = "hotelRooms")
@Table(name = "hotel_rooms")
public class HotelRoomEntity extends AbstractManageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hotel_room_id")
  private Long hotelRoomId;

  @Column(name = "hotels_hotel_id")
  private Long hotelId;

  @Column(name = "room_number")
  private String roomNumber;

  @Column(name = "room_type")
  @Convert(converter = HotelRoomTypeConverter.class)
  private HotelRoomType roomType;

  @Column(name = "original_price")
  private BigDecimal originalPrice;

  // 반드시 AbstractManageEntity 의 생성자를 호출해야 함
  public HotelRoomEntity() {
    super();
  }

  public static HotelRoomEntity of(String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
    HotelRoomEntity hotelRoomEntity = new HotelRoomEntity();
    hotelRoomEntity.roomNumber = roomNumber;
    hotelRoomEntity.roomType = hotelRoomType;
    hotelRoomEntity.originalPrice = originalPrice;

    return hotelRoomEntity;
  }
}
