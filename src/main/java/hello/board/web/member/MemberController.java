package hello.board.web.member;

import hello.board.repository.member.Member;
import hello.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/addMemberForm";
        }

        memberRepository.save(member);

        return "redirect:/";
    }

}
