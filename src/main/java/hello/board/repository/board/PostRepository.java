package hello.board.repository.board;

import hello.board.domain.Post;
import hello.board.web.post.PostUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    void update(Long bno, PostUpdateDto updateParam);

    Optional<Post> findByBno(Long bno);

    List<Post> findAll();

}
