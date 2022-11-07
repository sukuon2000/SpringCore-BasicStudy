package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {
    @Test
    @DisplayName("VIP는 10%의 할인이 적용되어야 한다")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        // import static 으로 해주면 Assertions가 줄어들면서 간결해진다.
        assertThat(discount).isEqualTo(1000);
    }


    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberA", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);
    }
}