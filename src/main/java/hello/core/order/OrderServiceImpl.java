package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    // DiscountPolicy의 파라미터명을 discountPolicy가 아닌
    // rateDiscountPolicy로 지정해놓으면
    // 빈이 두개가 발견되어도 RateDiscountPolicy를 가리키게 된다다
   @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    // 수정자 주입 (setter 주입)
    // setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다
    // 특징
    // 1. "선택, 변경" 가능성이 있는 의존관계에 사용
    // 2. 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.

    /*
    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */

    // 참고 : @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다
    // 주입할 대상이 없어도 동작하게 하려면
    // @Autowired(required = false) 로 지정하면 된다

    // 생성자가 단 하나인 경우, 자동을 @Autowired 설정이 된다
    // 과거에는 수정자 주입과 필드 주입을 많이 사용했지만, 최근에는 스프링을 포함한 DI 프레임워크 대부분이
    // 생성자 주입을 권장한다.
    // 이유 : "불변"
    // 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다
    // 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다 (불변해야한다)
    // 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다
    // 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
    // 생성자 주입은 객체를 생성할 때 딱 한번만 호출되므로 이후에 호출되는 일이 없다.
    // 따라서 불변하게 설계할 수 있다.


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
