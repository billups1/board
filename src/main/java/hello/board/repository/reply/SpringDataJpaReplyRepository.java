package hello.board.repository.reply;

import hello.board.repository.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select i from Reply i where i.bno = :bno")
    List<Reply> findByBno(@Param("bno") long bno);
}
