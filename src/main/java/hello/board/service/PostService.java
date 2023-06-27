package hello.board.service;

import hello.board.domain.Post;
import hello.board.repository.board.PostRepository;
import hello.board.repository.board.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post writePost(Post post) {
        return postRepository.save(post);
    }

    public void updatePost(Long bno, PostUpdateDto updateParam) {
        postRepository.update(bno, updateParam);
    }

    public List<Post> findPost() {
        return postRepository.findAll();
    }

    public Post findOne(Long bno) {
        return postRepository.findByBno(bno).orElse(null);
    }

}
