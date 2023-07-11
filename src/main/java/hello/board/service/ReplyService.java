package hello.board.service;

import hello.board.domain.Reply;
import hello.board.repository.reply.ReplyRepository;
import hello.board.repository.reply.ReplyUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Reply writeReply(Reply reply) {
        return replyRepository.save(reply);
    }

    public void updateReply(Long rno, ReplyUpdateDto updateParam) {
        replyRepository.update(rno, updateParam);
    }

}
