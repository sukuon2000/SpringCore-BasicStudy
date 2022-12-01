package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // AppConfig에서는 의존관계 주입을 해주는 방식이었는데
    // @Component를 명시하면 Bean등록이 자동으로 되는데
    // 의존관계를 설정할 방법이 없다.
    // 그래서, @Autowired 를 통해 자동 의존관계를 추가한다
    // 그래서 @ComponentScan을 쓰게되면, @Component 와 @Autowired 를 쓰게 된다
    // @Autowired 를 사용하면 생성자에서 여러 의존관계도 한번에 주입받을 수 있다.
    @Autowired  // ac.getBean(MemberRepository.class)  이런식으로 동작한다 생각하면 되겠다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
