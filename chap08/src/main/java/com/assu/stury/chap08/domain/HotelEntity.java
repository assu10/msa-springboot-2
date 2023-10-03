package com.assu.stury.chap08.domain;

import jakarta.persistence.*;
import lombok.Getter;

//@NoArgsConstructor
@Entity
@Getter
@Table(name = "hotels")
public class HotelEntity extends AbstractManageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hotel_id")
  private Long hotelId;

//  @Column(name = "status")
//  private HotelStatus status;

//  @Column
//  @Enumerated(value = EnumType.STRING)
//  private HotelStatusTemp status;

  @Column
  @Convert(converter = HotelStatus.class)
  private HotelStatus status;

  @Column
  private String name;

  @Column
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "room_count")
  private Integer roomCount;

  // 반드시 AbstractManageEntity 의 생성자를 호출해야 함
  public HotelEntity() {
    super();
  }

  public static HotelEntity of(String name, String address, String phoneNumber, Integer roomCount) {
    HotelEntity hotelEntity = new HotelEntity();

    hotelEntity.name = name;
    hotelEntity.status = HotelStatus.READY;
    hotelEntity.address = address;
    hotelEntity.phoneNumber = phoneNumber;
    hotelEntity.roomCount = roomCount;

    return hotelEntity;
  }

//  public enum HotelStatusTemp {
//    OPEN, CLOSED, READY
//  }
}
