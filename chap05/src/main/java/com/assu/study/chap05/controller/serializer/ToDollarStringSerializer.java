package com.assu.study.chap05.controller.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

// 추상 클래스인 JsonSerializer 를 상속받으며, 변환할 대상의 클래스 타입을 Generic 으로 설정
// 여기서 변환할 대상 클래스 타입은 BigDecimal
public class ToDollarStringSerializer extends JsonSerializer<BigDecimal> {
  @Override
  public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    // JSON 문자열을 만드는 JsonGenerator 객체의 writeString() 메서드를 사용하여 BicDecimal 객체를 문자열로 변경
    jsonGenerator.writeString(bigDecimal.setScale(2).toString());
  }
}
