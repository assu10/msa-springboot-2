package com.assu.study.chap06.domain.email;

import lombok.Getter;

import java.util.Objects;

// DTO
@Getter
public class EmailAddress {
  private static final String AT = "@";

  private final String name;
  private final String domainPart;
  private final String localPart;

  public EmailAddress(String name, String domainPart, String localPart) {
    this.name = name;
    this.domainPart = domainPart;
    this.localPart = localPart;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (Objects.nonNull(name)) {
      sb.append(name).append(" ");
    }
    return sb.append("<").append(localPart).append(AT).append(domainPart).append(">").toString();
  }
}
