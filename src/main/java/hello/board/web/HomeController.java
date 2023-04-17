package hello.board.web;

import hello.board.repository.board.Post;
import hello.board.repository.board.PostRepository;
import hello.board.repository.board.PostWriteDto;
import hello.board.repository.member.Member;
import hello.board.repository.member.MemberRepository;
import hello.board.repository.reply.ReplyRepository;
import hello.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model, HttpServletRequest request) {

        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);

        return "loginHome";
    }

}
