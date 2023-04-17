package hello.board.repository.reply;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class ReplyWriteDto {

    @NotBlank
    private String content;

}
