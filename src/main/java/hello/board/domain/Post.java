package hello.board.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Slf4j
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_bno")
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
    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UploadFile attachFile;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UploadImage> imageFiles = new ArrayList<>();
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reply> reply;


    public Post() {
    }

    public Post(Member member, String title, String content, String date, String whetherLogin, UploadFile attachFile, List<UploadImage> imageFiles) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.date = date;
        this.whetherLogin = whetherLogin;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }

    public void setAttachFile(UploadFile attachFile) {
        this.attachFile = attachFile;
        attachFile.setPost(this);
    }
    public void addImageFiles(UploadImage imageFiles) {
        this.imageFiles.add(imageFiles);
        imageFiles.setPost(this);
    }

    public static Post createPost(Member member, String title, String content, String date, String whetherLogin, UploadFile attachFile, List<UploadImage> imageFiles) {
        Post post = new Post();
        post.setMember(member);
        post.setTitle(title);
        post.setContent(content);
        post.setDate(date);
        post.setWhetherLogin(whetherLogin);
        if (attachFile != null) {
            post.setAttachFile(attachFile);
        }
        for (UploadImage imageFile : imageFiles) {
            log.info("imageFile={}",imageFile);
            post.addImageFiles(imageFile);
        }
        return post;
    }


}
