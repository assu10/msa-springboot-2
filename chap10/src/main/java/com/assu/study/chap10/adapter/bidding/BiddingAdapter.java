package com.assu.study.chap10.adapter.bidding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BiddingAdapter {
    private final String PREFIX = "HOTEL-BIDDING::";
    private final RedisTemplate<String, Long> biddingRequestTemplate;

    public BiddingAdapter(RedisTemplate<String, Long> biddingRequestTemplate) {
        this.biddingRequestTemplate = biddingRequestTemplate;
    }

    // 이벤트에 참가하는 호텔 아이디를 사용하여 Sorted Set 의 key 를 생성
    // 별도의 RedisSerializer 구현체를 생성하지 않고 이렇게 해도 됨
    private String serializeKey(Long hotelId) {
        return PREFIX + hotelId;
    }

    // 입찰에 참여할 호텔 아이디, 사용자 아이디, 비딩 금액을 받음
    public Boolean createBidding(Long hotelId, Long userId, Double amount) {
        String key = this.serializeKey(hotelId);

        // ZSetOperations 의 add() 메서드를 사용하여 호텔 아이디를 포함한 key, userId 에 value 와 비딩 금액을 score 로 저장
        return biddingRequestTemplate.opsForZSet().add(key, userId, amount);
    }

    // 입찰에 참여한 사용자들의 비딩 금액을 역순으로 정렬한 후 fetchCount 만큼 참가자의 아이디를 리스트 객체로 리턴
    public List<Long> getTopBidders(Long hotelId, Integer fetchCount) {
        String key = this.serializeKey(hotelId);

        // 비딩 금액이 높은 순으로 정렬해야 하기 때문에 score 를 역순으로 조회하는 reverseRangeByScore() 사용
        // 이 때 score 범위를 인자로 설정할 수 있는데 두 번째 인자인 최소값 0D 부터 최대값 Double.MAX_VALUE 까지 설정
        // 네 번째 인자는 순서 인덱스로 0 부터 fetchCount 개수만큼 조회함
        return biddingRequestTemplate
                .opsForZSet()
                .reverseRangeByScore(key, 0D, Double.MAX_VALUE, 0, fetchCount)
                .stream()
                .collect(Collectors.toList());
    }

    // 참여자가 입찰한 금액 조회
    public Double getBigAmount(Long hotelId, Long userId) {
        String key = this.serializeKey(hotelId);

        // score() 로 score 값 조회
        // 따라서 score 에 저장한 입찰 금액 리턴
        return biddingRequestTemplate.opsForZSet().score(key, userId);
    }

    public void clear(Long hotelId) {
        String key = this.serializeKey(hotelId);
        biddingRequestTemplate.delete(key);
    }
}
