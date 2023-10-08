package com.assu.stury.chap08.domain;

import com.assu.stury.chap08.domain.converter.HotelStatusConverter;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
// @EqualsAndHashCode
// @ToString
@Getter
@Table(name = "hotels")
@Entity(name = "hotels")
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

  @Column(name = "status")
  @Convert(converter = HotelStatusConverter.class)
  private HotelStatus status;

  @Column
  private String name;

  @Column
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "room_count")
  private Integer roomCount;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "hotels_hotel_id", referencedColumnName = "hotel_id")
  private List<HotelRoomEntity> hotelRoomEntities;

  // 반드시 AbstractManageEntity 의 생성자를 호출해야 함
  public HotelEntity() {
    super();
    this.hotelRoomEntities = new ArrayList<>();
  }


  public static HotelEntity of(String name, String address, String phoneNumber) {
    HotelEntity hotelEntity = new HotelEntity();

    hotelEntity.name = name;
    hotelEntity.status = HotelStatus.READY;
    hotelEntity.address = address;
    hotelEntity.phoneNumber = phoneNumber;
    hotelEntity.roomCount = 0;

    return hotelEntity;
  }

  public void addHotelRooms(List<HotelRoomEntity> hotelRoomEntities) {
    this.roomCount += hotelRoomEntities.size();
    this.hotelRoomEntities.addAll(hotelRoomEntities);
  }
//  public enum HotelStatusTemp {
//    OPEN, CLOSED, READY
//  }
}
