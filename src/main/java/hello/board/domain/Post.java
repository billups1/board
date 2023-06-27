package hello.board.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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

    public Post(Member member, String title, String content, String date, String whetherLogin) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.date = date;
        this.whetherLogin = whetherLogin;
    }
}
