package hello.board.web.post;

import hello.board.domain.*;
import hello.board.repository.reply.ReplyWriteDto;
import hello.board.service.PostService;
import hello.board.service.ReplyService;
import hello.board.web.SessionConst;
import hello.board.web.post.file.FileStore;
import hello.board.web.post.file.ImageStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final ReplyService replyService;
    private final FileStore fileStore;
    private final ImageStore imageStore;

    @GetMapping("/postNew")
    public String postNew(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member member, Model model) {
        if (member == null) {
            return "post/postNewUnLogin";
        }
        if (model.getAttribute("post") == null) {
            model.addAttribute("post", new Post());
        }
        model.addAttribute("member", member);
        return "post/postNewLogin";
    }

    @PostMapping("/postNew")
    public String postWrite(@Validated @ModelAttribute("post") PostWriteDto postWriteDto, BindingResult bindingResult, Model model,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) throws IOException {
        String title = postWriteDto.getTitle();
        String content = postWriteDto.getContent();
        MultipartFile attachFile = postWriteDto.getAttachFile();
        List<MultipartFile> imageFiles = postWriteDto.getImageFiles();

        if (bindingResult.hasErrors()) {
            Post post = new Post();
            post.setTitle(postWriteDto.getTitle());
            post.setContent(postWriteDto.getContent());
            model.addAttribute("post", post);
            model.addAttribute("member", member);
            return "post/postNewLogin";
        }

        LocalDateTime now = LocalDateTime.now();
        String whetherLogin = "";
        if (member != null) {
            whetherLogin = "O";
        }

        UploadFile uploadAttachFile = fileStore.storeFile(attachFile);
        List<UploadImage> uploadImages = imageStore.storeFiles(imageFiles);

        postService.writePost(member, title, content, now.format(DateTimeFormatter.BASIC_ISO_DATE), whetherLogin, uploadAttachFile, uploadImages);
        return "redirect:/";
    }

    @GetMapping("/post/{bno}")
    public String post(@PathVariable long bno, Model model,@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member) {
        Post post = postService.findOne(bno);
        model.addAttribute("member", member);
        model.addAttribute("post", post);
        model.addAttribute("replies", post.getReply());
        model.addAttribute("reply", new Reply());
        return "post/post";
    }

    @GetMapping("/attach/{postId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long postId) throws MalformedURLException {
        Post post = postService.findOne(postId);
        String storeFileName = post.getAttachFile().getStoreFileName();
        String uploadFileName = post.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(urlResource);
    }


    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable String fileName) throws MalformedURLException {
        log.info("aaa");
        return new UrlResource("file:"+fileStore.getFullPath(fileName));
    }

    @PostMapping("/post/{bno}")
    public String replySave(@PathVariable long bno, @ModelAttribute("replyContent") ReplyWriteDto updateParam,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member, RedirectAttributes redirectAttributes) {
        Reply reply = new Reply();
        LocalDateTime now = LocalDateTime.now();
        reply.setPost(postService.findOne(bno));
        reply.setMember(member);
        reply.setContent(updateParam.getContent());
        reply.setDate(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        if (member != null) {
            reply.setWhetherLogin("O");
        }
        replyService.writeReply(reply);

        redirectAttributes.addAttribute("bno", bno);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/post/{bno}";
    }

    @GetMapping("/post/edit/{bno}")
    public String postEditGet(@PathVariable long bno, Model model) {
        Post post = postService.findOne(bno);
        model.addAttribute("post", post);
        return "post/postEdit";
    }

    @PostMapping("/post/edit/{bno}")
    public String postEdit(@PathVariable long bno, @ModelAttribute PostUpdateDto updateParam) {
        postService.updatePost(bno, updateParam);
        return "redirect:/post/"+bno;
    }

}
