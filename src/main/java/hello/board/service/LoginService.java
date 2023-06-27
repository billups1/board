package hello.board.service;

import hello.board.domain.Member;
import hello.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {

    private final MemberRepository repository;

    public Member login(String loginId, String password) {
        return repository.findByLoginId(loginId).filter(m -> m.getPassword().equals(password)).orElse(null);
    }


}
