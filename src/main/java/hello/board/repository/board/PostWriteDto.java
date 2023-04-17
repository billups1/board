package hello.board.repository.board;

import lombok.Data;
import lombok.Getter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PostWriteDto {

    private String bno;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public PostWriteDto() {
    }

}
