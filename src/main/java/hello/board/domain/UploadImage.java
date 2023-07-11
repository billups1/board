package hello.board.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UploadImage {

    @Id
    @Column(name = "upload_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uploadFileName;
    private String storeFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_bno")
    private Post post;

    public UploadImage(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
