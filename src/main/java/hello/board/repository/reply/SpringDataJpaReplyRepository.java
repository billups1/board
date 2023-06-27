package hello.board.repository.reply;

import hello.board.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataJpaReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select i from Reply i where i.bno = :bno")
    List<Reply> findByBno(@Param("bno") long bno);
}
