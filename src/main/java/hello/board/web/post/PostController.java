package hello.board.web.post;

import hello.board.repository.board.Post;
import hello.board.repository.board.PostRepository;
import hello.board.repository.board.PostUpdateDto;
import hello.board.repository.board.PostWriteDto;
import hello.board.repository.member.Member;
import hello.board.repository.reply.Reply;
import hello.board.repository.reply.ReplyRepository;
import hello.board.repository.reply.ReplyWriteDto;
import hello.board.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    @GetMapping("/postNew")
    public String postNew(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member member, Model model) {
        if (member == null) {
            return "post/postNewUnLogin";
        }
        if (model.getAttribute("post") == null) {
            model.addAttribute("post", new Post());
        }
        model.addAttribute("member", member);
        return "post/postNewLogin";
    }

    @PostMapping("/postNew")
    public String postWrite(@Validated @ModelAttribute("post") PostWriteDto postWriteDto, BindingResult bindingResult, Model model,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) {
        String title = postWriteDto.getTitle();
        String content = postWriteDto.getContent();

        if (bindingResult.hasErrors()) {
            Post post = new Post();
            post.setTitle(postWriteDto.getTitle());
            post.setContent(postWriteDto.getContent());
            model.addAttribute("post", post);
            model.addAttribute("member", member);
            return "post/postNewLogin";
        }

        String memberName = member.getName();
        LocalDateTime now = LocalDateTime.now();
        String whetherLogin = "";
        if (member != null) {
            whetherLogin = "O";
        }

        Post post = new Post(memberName, title, content, now.format(DateTimeFormatter.BASIC_ISO_DATE), whetherLogin);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/{bno}")
    public String post(@PathVariable long bno, Model model,@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member) {
        Post post = postRepository.findByBno(bno).orElse(null);
        List<Reply> replies = replyRepository.findByBno(bno);
        model.addAttribute("member", member);
        model.addAttribute("post", post);
        model.addAttribute("replies", replies);
        model.addAttribute("reply", new Reply());
        return "post/post";
    }

    @PostMapping("/post/{bno}")
    public String replySave(@PathVariable long bno, @ModelAttribute("replyContent") ReplyWriteDto updateParam,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member, RedirectAttributes redirectAttributes) {
        Reply reply = new Reply();
        LocalDateTime now = LocalDateTime.now();
        reply.setBno(bno);
        reply.setUsername(member.getName());
        reply.setContent(updateParam.getContent());
        reply.setDate(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        if (member != null) {
            reply.setWhetherLogin("O");
        }
        replyRepository.save(reply);

        redirectAttributes.addAttribute("bno", bno);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/post/{bno}";
    }

    @GetMapping("/post/edit/{bno}")
    public String postEditGet(@PathVariable long bno, Model model) {
        Post post = postRepository.findByBno(bno).orElse(null);
        model.addAttribute("post", post);
        return "post/postEdit";
    }

    @PostMapping("/post/edit/{bno}")
    public String postEdit(@PathVariable long bno, @ModelAttribute PostUpdateDto updateParam) {
        postRepository.update(bno, updateParam);
        return "redirect:/post/"+bno;
    }

}
