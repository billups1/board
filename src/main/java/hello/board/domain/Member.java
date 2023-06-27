package hello.board.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<Reply> replies = new ArrayList<>();

    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
