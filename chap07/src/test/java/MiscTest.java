import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MiscTest {

  // 테스트 클래스 인스턴스를 초기화할 때 가장 먼저 실행되며 한 번만 실행됨
  @BeforeAll
  public static void setup() {
    log.info("BeforeAll: before all tests in the current test class.");
  }

  // 테스트 클래스의 모든 테스트 메서드가 실행을 마치면 마지막으로 한 번만 실행됨
  @AfterAll
  public static void destroy() {
    log.info("AfterAll: after all tests in the current test class.");
  }

  // 모든 테스트 메서드가 실행되기 전 각각 한 번씩 실행됨
  @BeforeEach
  public void init() {
    log.info("BeforeEach: before each @Test.");
  }

  // HashSet 기능 테스트
  @Test
  public void testHashSetContainsNonDuplicatedValue() {
    log.info("Test: testHashSetContainsNonDuplicatedValue()");

    // Given
    Integer value = Integer.valueOf(1);
    Set<Integer> set = new HashSet<>();

    // When
    set.add(value);
    set.add(value);
    set.add(value);

    // Then
    Assertions.assertEquals(1, set.size());
    Assertions.assertTrue(set.contains(value));
  }

  @Test
  public void testDummy() {
    log.info("Test: testDummy()");
    Assertions.assertTrue(Boolean.TRUE);
  }

  // 모든 테스트 메서드가 실행되고 난 후 각각 한 번씩 실행됨
  @AfterEach
  public void cleanup() {
    log.info("AfterEach: after each @Test");
  }
}
