package com.assu.study.chap10.adapter.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class LockAdapterTest {

  private final Long firstUserId = 1L;
  private final Long secondUserId = 2L;
  private final Long thirdUserId = 3L;

  @Autowired
  private LockAdapter lockAdapter;

  @Test
  @DisplayName("firstUserId 가 락 선점")
  public void testLock() {
    final Long hotelId = 1111L;

    boolean isSuccess = lockAdapter.holdLock(hotelId, firstUserId);
    Assertions.assertTrue(isSuccess);

    isSuccess = lockAdapter.holdLock(hotelId, secondUserId);
    Assertions.assertFalse(isSuccess);

    Long holderId = lockAdapter.checkLock(hotelId);
    Assertions.assertEquals(firstUserId, holderId);
  }

  @Test
  @DisplayName("동시에 3이 락을 선점하지만 1명만 락을 잡음")
  public void testConcurrentAccess() throws InterruptedException {
    final Long hotelId = 9999L;

    // CyclicBarrier 의 인자를 3으로 설정
    // 각 스레드는 CyclicBarrier 공유 객체의 await() 메서드를 호출하고, 3번 호출되면 CyclicBarrier 는 스레드 실행함
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    // Runnable 을 구현하는 Accessor 를 사용하여 3개의 스레드 실행
    // 공유 자원인 hotelId 와 사용자 아이디를 각각 인자로 입력
    // 그리고 CyclicBarrier 기능을 공유하려고 인자로 넘김
    new Thread(new Accessor(hotelId, firstUserId, cyclicBarrier)).start();
    new Thread(new Accessor(hotelId, secondUserId, cyclicBarrier)).start();
    new Thread(new Accessor(hotelId, thirdUserId, cyclicBarrier)).start();

    TimeUnit.SECONDS.sleep(1);
    Long holderId = lockAdapter.checkLock(hotelId);

    log.info("holderId: {}", holderId);

    // 스레드를 실행하고 1초 후 락의 유무 확인
    // 단, firstUserId, secondUserId, thirdUserId 중 하나가 레디스 value 에 저장되어 있음을 검증함
    Assertions.assertTrue(List.of(firstUserId, secondUserId, thirdUserId).contains(holderId));
    lockAdapter.clearLock(hotelId);
  }

  // 최대한 동시에 LockAdapter 의 holdLock() 을 실행하고자 CyclicBarrier 사용
  class Accessor implements Runnable {
    private final Long hotelId;
    private final Long userId;
    private final CyclicBarrier cyclicBarrier;

    public Accessor(Long hotelId, Long userId, CyclicBarrier cyclicBarrier) {
      this.hotelId = hotelId;
      this.userId = userId;
      this.cyclicBarrier = cyclicBarrier;
    }

    // 인자로 받은 cyclicBarrier 객체를 사용하여 await() 메서드를 실행하고,
    // lockAdapter.holdLock() 메서드 실행
    // await() 가 3번 호출될 때까지는 모든 스레드는 대기하고,
    // 3번 호출된 시점에 모든 스레드가 한 번에 lockAdapter.holdLock() 를 호출함
    @Override
    public void run() {
      try {
        cyclicBarrier.await();
        lockAdapter.holdLock(hotelId, userId);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}