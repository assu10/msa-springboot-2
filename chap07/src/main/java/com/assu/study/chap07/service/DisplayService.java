package com.assu.study.chap07.service;

import com.assu.study.chap07.controller.HotelRequest;
import com.assu.study.chap07.controller.HotelResponse;

import java.util.List;

public interface DisplayService {
  List<HotelResponse> getHotelsByName(HotelRequest hotRequest);
}
