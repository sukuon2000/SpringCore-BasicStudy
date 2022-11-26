package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    //Thread A가 사용자A 코드를 호출하고, Thread B가 사용자B 코드를 호출한다 가정
    // StatefulService의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다
    // 사용자A의 주문금액은 10000원이 되어야 하는데 20000원이라는 결과가 나왔다
    // 실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다
    // 진짜 공유필드는 조심해야 한다!
    // 스프링 빈은 항상 무상태(stateless)로 설계하자
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        statefulService1.order("userB", 20000);
        // 이 때 StatefulService의 price가 20000으로 바꿔치기 되버린다

        // ThreadA : A사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 기대한건 10000원이지만 20000원이 나온다

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    // 무상태(stateless)로 만든 버전
    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        // ThreadA : A사용자 10000원 주문
        int userAOrderPrice = statelessService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        int userBOrderPrice = statelessService1.order("userB", 20000);

        assertThat(userAOrderPrice).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}