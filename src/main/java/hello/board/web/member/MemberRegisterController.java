package hello.board.web.member;

import hello.board.domain.Member;
import hello.board.repository.member.MemberRepository;
import hello.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberRegisterController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("member", new Member());
        return "memberRegister/memberRegister";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {
        Optional<Member> byLoginId = memberService.findByLoginId(member.getLoginId());
        if (!memberService.findByLoginId(member.getLoginId()).equals(Optional.empty())) {
            bindingResult.addError(new FieldError("member", "loginId", "ID가 중복되었습니다."));
        }

        if (bindingResult.hasErrors()) {
            return "memberRegister/memberRegister";
        }

        memberService.join(member);
        return "redirect:/";
    }


}
