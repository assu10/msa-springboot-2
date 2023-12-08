package com.assu.study.chap12.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

// 이벤트 메시지 클래스
// 유저를 생성/삭제할 때 게시할 목적으로 사용됨
@Getter
public class UserEvent extends ApplicationEvent {   // 이벤트 메시지 클래스이므로 ApplicationEvent 상속
    private final Type type;
    private final Long userId;
    private final String email;

    private UserEvent(Object source, Type type, Long userId, String email) {
        // Object source 는 이벤트를 게시하는 클래스의 객체를 의미
        // 예를 들어 UserService 클래스에서 UserEvent 객체를 생성/게시할 때 UserService 객체를 Object source 인자로 넘김
        // ApplicationEvent 객체에 할당된 Object source 는 Object getSource() 로 참조 가능
        super(source);

        this.type = type;
        this.userId = userId;
        this.email = email;
    }

    // 정적 팩토리 메서드
    public static UserEvent created(Object source, Long userId, String email) {
        return new UserEvent(source, Type.CREATE, userId, email);
    }

    public enum Type {
        CREATE, DELETE
    }
}
