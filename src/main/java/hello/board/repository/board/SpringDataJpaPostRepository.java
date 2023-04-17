package hello.board.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByBno(Long bno);
}
