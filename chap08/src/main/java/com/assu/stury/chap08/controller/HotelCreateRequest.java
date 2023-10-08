package com.assu.stury.chap08.controller;

import lombok.Getter;
import lombok.Setter;

// DTO
@Getter
@Setter
public class HotelCreateRequest {
  private String name;
  private String address;
  private String phoneNumber;
  private Integer roomCount;
}
