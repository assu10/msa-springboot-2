package com.assu.stury.chap08.domain;

import com.assu.stury.chap08.server.UserIdHolder;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
@MappedSuperclass // 상속할 수 있도록 애너테이션 정의
@Getter
// 단독으로 매핑되는 테이블이 없으므로 영속할 수 없다.
// 따라서 abstract 제어자를 사용하여 클래스가 단독으로 생성되는 상황을 막는다.
public abstract class AbstractManageEntity {
  @Column(name = "created_at")
  private final ZonedDateTime createdAt;

  @Column(name = "created_by")
  private final String createdBy;

  @Column(name = "modified_at")
  private ZonedDateTime modifiedAt;

  @Column(name = "modified_by")
  private String modifiedBy;

  public AbstractManageEntity() {
    this.createdAt = ZonedDateTime.now();
    this.createdBy = UserIdHolder.getUserId();
  }
}
