package hello.board.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;
    @ManyToOne
    @JoinColumn(name = "post_bno")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @NotBlank
    private String content;
    @NotBlank
    private String date;
    private String whetherLogin;



}
