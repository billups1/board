package hello.board.config;

import hello.board.domain.login.LoginService;
import hello.board.repository.board.JpaPostRepository;
import hello.board.repository.board.PostRepository;
import hello.board.repository.board.SpringDataJpaPostRepository;
import hello.board.repository.member.JpaMemberRepository;
import hello.board.repository.member.MemberRepository;
import hello.board.web.login.LoginController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManager;


@RequiredArgsConstructor
@Configuration
public class Config {

//    private final SpringDataJpaPostRepository postRepository;
    private final EntityManager em;
//
//    @Bean
//    public PostRepository postRepository() {
//        return new JpaPostRepository(postRepository);
//    }
//
    @Bean
    @Primary
    MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }


}
