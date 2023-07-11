package hello.board.web.post;

import lombok.Data;
import lombok.Getter;
import org.aspectj.weaver.ast.Not;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PostWriteDto {

    private String bno;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;

    public PostWriteDto() {
    }

}
