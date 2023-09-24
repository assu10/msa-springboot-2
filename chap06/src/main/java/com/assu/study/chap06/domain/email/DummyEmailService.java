package com.assu.study.chap06.domain.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!email")  // 프로파일 중 email 값이 없는 애플리케이션에서는 DummyEmailService 구현체를 스프링 빈으로 생성
public class DummyEmailService implements EmailService {
  @Override
  public boolean sendEmail(EmailAddress emailAddress) {
    System.out.println("dummy email: " + emailAddress.toString());
    return true;
  }
}
