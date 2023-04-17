package hello.board.repository.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    void update(Long id, MemberUpdateDto updateParam);

    Optional<Member> findById(Long id);
    Optional<Member> findByLoginId(String LoginId);

    List<Member> findAll();

}
