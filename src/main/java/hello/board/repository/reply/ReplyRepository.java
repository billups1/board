package hello.board.repository.reply;

import hello.board.domain.Reply;

import java.util.List;

public interface ReplyRepository {

    Reply save(Reply reply);

    void update(Long rno, ReplyUpdateDto updateParam);

    List<Reply> findByBno(long bno);

}
