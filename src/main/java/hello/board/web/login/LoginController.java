package hello.board.web.login;

import hello.board.domain.login.LoginService;
import hello.board.repository.member.Member;
import hello.board.web.SessionConst;
import hello.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;
//    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL) {
        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = service.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

//        sessionManager.createSession(loginMember, response);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:"+redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
//        sessionManager.expire(request);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/" ;
    }

}
