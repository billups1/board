package hello.board.repository.reply;

import hello.board.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaReplyRepository implements ReplyRepository{

    private final SpringDataJpaReplyRepository repository;

    @Override
    public Reply save(Reply reply) {
        repository.save(reply);
        return reply;
    }

    @Override
    public void update(Long rno, ReplyUpdateDto updateParam) {
        Reply reply = repository.findById(rno).orElse(null);
//        reply.setContent(updateParam.getContent());
    }

    @Override
    public List<Reply> findByBno(long bno) {
        List<Reply> replies = repository.findByBno(bno);
        return replies;
    }
}
