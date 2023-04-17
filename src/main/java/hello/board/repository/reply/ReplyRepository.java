package hello.board.repository.reply;

import hello.board.repository.board.Post;
import hello.board.repository.board.PostUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    Reply save(Reply reply);

    void update(Long rno, ReplyUpdateDto updateParam);

    List<Reply> findByBno(long bno);

}
