package hello.board.web.post;

import hello.board.domain.Post;
import hello.board.repository.board.PostUpdateDto;
import hello.board.repository.board.PostWriteDto;
import hello.board.domain.Member;
import hello.board.domain.Reply;
import hello.board.repository.reply.ReplyWriteDto;
import hello.board.service.PostService;
import hello.board.service.ReplyService;
import hello.board.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ReplyService replyService;

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

        LocalDateTime now = LocalDateTime.now();
        String whetherLogin = "";
        if (member != null) {
            whetherLogin = "O";
        }

        Post post = new Post(member, title, content, now.format(DateTimeFormatter.BASIC_ISO_DATE), whetherLogin);
        postService.writePost(post);
        return "redirect:/";
    }

    @GetMapping("/post/{bno}")
    public String post(@PathVariable long bno, Model model,@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member) {
        Post post = postService.findOne(bno);
        List<Reply> replies = replyService.findReplyByBno(bno);
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
        reply.setMember(member);
        reply.setContent(updateParam.getContent());
        reply.setDate(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        if (member != null) {
            reply.setWhetherLogin("O");
        }
        replyService.writeReply(reply);

        redirectAttributes.addAttribute("bno", bno);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/post/{bno}";
    }

    @GetMapping("/post/edit/{bno}")
    public String postEditGet(@PathVariable long bno, Model model) {
        Post post = postService.findOne(bno);
        model.addAttribute("post", post);
        return "post/postEdit";
    }

    @PostMapping("/post/edit/{bno}")
    public String postEdit(@PathVariable long bno, @ModelAttribute PostUpdateDto updateParam) {
        postService.updatePost(bno, updateParam);
        return "redirect:/post/"+bno;
    }

}
