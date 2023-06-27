package hello.board.web;

import hello.board.domain.Post;
import hello.board.repository.board.PostRepository;
import hello.board.domain.Member;
import hello.board.repository.member.MemberRepository;
import hello.board.repository.reply.ReplyRepository;
import hello.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final PostService postService;


    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model, HttpServletRequest request) {

        List<Post> posts = postService.findPost();
        model.addAttribute("posts", posts);

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);

        return "loginHome";
    }

}
