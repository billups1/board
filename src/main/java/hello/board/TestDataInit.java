package hello.board;

import hello.board.repository.member.Member;
import hello.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//
//@Component
//@RequiredArgsConstructor
//public class TestDataInit {
//
//    private final MemberRepository memberRepository;
//
//    @PostConstruct
//    void init() {
//        memberRepository.save(new Member("aaa", "111", "123"));
//        memberRepository.save(new Member("bbb", "222", "123"));
//    }
//
//}
