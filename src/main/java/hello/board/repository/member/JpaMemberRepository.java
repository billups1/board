package hello.board.repository.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public void update(Long id, MemberUpdateDto updateParam) {
        Member findMember = em.find(Member.class, id);
        findMember.setName(updateParam.getName());
        findMember.setPassword(updateParam.getPassword());
        findMember.setLoginId(updateParam.getLoginId());
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginId(String LoginId) {
        return findAll().stream().filter(member -> member.getLoginId().equals(LoginId)).findAny();
    }

    @Override
    public List<Member> findAll() {
        String jpql = "select i from Member i";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        return query.getResultList();
    }
}
