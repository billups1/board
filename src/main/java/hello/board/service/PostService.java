package hello.board.service;

import hello.board.domain.Member;
import hello.board.domain.Post;
import hello.board.domain.UploadFile;
import hello.board.domain.UploadImage;
import hello.board.repository.board.PostRepository;
import hello.board.web.post.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public Post writePost(Member member, String title, String content, String date, String whetherLogin, UploadFile uploadAttachFile, List<UploadImage> uploadImages) {
        log.info("uploadAttachFile={}", uploadAttachFile);
        log.info("uploadImages={}", uploadImages);
        Post post = Post.createPost(member, title, content, date, whetherLogin, uploadAttachFile, uploadImages);
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
