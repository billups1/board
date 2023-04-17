package hello.board.domain.login;

import hello.board.repository.member.Member;
import hello.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository repository;

    public Member login(String loginId, String password) {
        return repository.findByLoginId(loginId).filter(m -> m.getPassword().equals(password)).orElse(null);
    }


}
