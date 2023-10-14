package com.assu.study.chap09.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateCodeRequest {
  private Integer type;

  @JsonProperty("hotelIds") // JSON 객체로 마셜링하는 과정에서 ids 속성명 대신 hotelIds 가 JSON 객체의 속성명이 됨
  private List<Long> ids;

  public CreateCodeRequest(Integer type, List<Long> ids) {
    this.type = type;
    this.ids = ids;
  }
}
