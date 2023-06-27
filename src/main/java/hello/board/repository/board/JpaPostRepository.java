package hello.board.repository.board;

import hello.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPostRepository implements PostRepository{
    private final SpringDataJpaPostRepository repository;

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public void update(Long id, PostUpdateDto updateParam) {
        Post post = repository.findById(id).orElseThrow();
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
    }

    @Override
    public Optional<Post> findByBno(Long id) {
        return repository.findByBno(id);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }
}
