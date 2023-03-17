package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainter(){
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        /* 우리가 만들었던 스프링없는 순수한 DI컨테이너인 AppConfig는 요청할 때마다 객체를 새로 생성함
        * 고객 트래픽이 초당 100이 나오면 초당 100개씩 객체가 생성되고 소멸됨 -> 메모리 낭비가 심하다
        * 해결방안 해당 객체가 1개씩만 생성되고 공유하도록 설계 -> 싱글톤 패턴
         */
        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != meberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
