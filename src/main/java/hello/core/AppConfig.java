package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//@Configuartion 싱글톤을 보장해줌
//@Bean만 사용해도 스프링 빈 등ㅇ록되지만 싱글톤 보장하지 않는다.
@Configuration
public class AppConfig {
    //@Bean memberService -> new MemorymemberRepository() 호출
    //@Bean orderService -> new MemorymemberRepository() 호출
    //각각 MemorymemberRepository가 생성되면서 싱글톤이 깨지는 것처럼 보인다.

    //MemberService 역할
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    //MemberRepository 역할
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberService");
        return new MemoryMemberRepository();
    }
    //OrderService 역할
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.memberService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    // DiscountPolicy 역할
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        // AppConfig에서 RateDiscountPolicy로만 교체하면 됨
        return new RateDiscountPolicy();
    }
}
