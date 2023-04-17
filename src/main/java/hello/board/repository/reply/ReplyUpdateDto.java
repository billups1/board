package hello.board.repository.reply;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ReplyUpdateDto {

    @NotBlank
    private String content;

    public ReplyUpdateDto(String content) {
        this.content = content;
    }
}
