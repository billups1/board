package hello.board.web.memberRegister;

import hello.board.repository.member.Member;
import hello.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberRegisterController {
    private final MemberRepository memberRepository;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("member", new Member());
        return "memberRegister/memberRegister";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {
        Optional<Member> byLoginId = memberRepository.findByLoginId(member.getLoginId());
        if (!memberRepository.findByLoginId(member.getLoginId()).equals(Optional.empty())) {
            bindingResult.addError(new FieldError("member", "loginId", "ID가 중복되었습니다."));
        }

        if (bindingResult.hasErrors()) {
            return "memberRegister/memberRegister";
        }

        memberRepository.save(member);
        return "redirect:/";
    }


}
