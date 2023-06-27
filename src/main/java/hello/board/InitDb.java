package hello.board;

import hello.board.domain.Member;
import hello.board.repository.member.MemberRepository;
import hello.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setName("master");
        member.setPassword("123");
        member.setLoginId("123");
        memberService.join(member);

    }

}
