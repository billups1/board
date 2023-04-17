package hello.board.repository.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberUpdateDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

}
