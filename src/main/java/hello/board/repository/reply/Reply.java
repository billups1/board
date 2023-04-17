package hello.board.repository.reply;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;
    private long bno;
    @NotBlank
    private String username;
    @NotBlank
    private String content;
    @NotBlank
    private String date;
    private String whetherLogin;



}
