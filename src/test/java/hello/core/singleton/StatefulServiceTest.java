package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statfulService1 = ac.getBean(StatefulService.class);
        StatefulService statfulService2 = ac.getBean(StatefulService.class);

        //TreadA: A사용자 10000원 주문
        int UserAprice = statfulService1.order("userA",10000);
        //TreadB: B사용자 20000원 주문
        int UserBprice = statfulService2.order("userB",20000);

        //TreadA: 사용자A 주문 금액 조회
       // int price = statfulService1.getPrice();
        System.out.println("price = " + UserAprice);

       // Assertions.assertThat(statfulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}