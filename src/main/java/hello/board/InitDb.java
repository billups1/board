package hello.board;

import hello.board.domain.Member;
import hello.board.domain.Post;
import hello.board.domain.UploadFile;
import hello.board.domain.UploadImage;
import hello.board.repository.member.MemberRepository;
import hello.board.service.MemberService;
import hello.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final MemberService memberService;
    private final PostService postService;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setName("master");
        member.setPassword("123");
        member.setLoginId("123");
        memberService.join(member);
        UploadFile attachFile = new UploadFile("h2.txt", "b3891208-2c43-4ec0-b2a0-5ed83b8d3617.txt");
        List<UploadImage> uploadImages = new ArrayList<>();
        uploadImages.add(new UploadImage("다운로드.png", "a773675d-0f17-46f4-9088-365e6601bac2.png"));
        postService.writePost(member, "hello", "asdasd", LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE), "O", attachFile, uploadImages);
    }

}
