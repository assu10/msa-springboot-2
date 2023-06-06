package com.assu.study.chap03.domain.format;

public interface Formatter<T> {
    // 제네릭 T 를 인자로 받아 인수를 적절한 형태로 포맷팅하는 메서드
    String of(T target);
}
