package hello.board.repository.board;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @NotBlank
    private String username;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String date;
    private String whetherLogin;

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String username, String title, String content, String date, String whetherLogin) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
        this.whetherLogin = whetherLogin;
    }
}
