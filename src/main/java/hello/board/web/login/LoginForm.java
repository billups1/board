package hello.board.web.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

}
