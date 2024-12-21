package com.pulse.demo.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.demo.Modals.CommentModal;
import com.pulse.demo.Modals.PostModal;
import com.pulse.demo.Services.PostService;

@RestController
@RequestMapping(path = "post")
public class PostController {

    @GetMapping(path = "/{postId}")
    public PostModal getPostBypostId(@PathVariable("postId") String id) {
        return PostService.getPostByPostId(id);
    }

    @GetMapping(path = "/user/{userId}")
    public PostModal getPostByUserId(@PathVariable("userId") String id) {
        return PostService.getPostByUserId(id);
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewPost(@RequestBody PostModal post) {
        PostService.addNewPost(post);
        return ResponseEntity.ok("Post added successfully!");
    }

    @PutMapping("/post/addlike/{postId}")
    public ResponseEntity<String> addPostLike(@PathVariable("postId") String postId) {
        PostService.addLike(postId);
        return ResponseEntity.ok("Like added for the post Successfully!");
    }

    @PutMapping("/addComment")
    public ResponseEntity<String> addPostComment(@RequestBody CommentModal comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        comment.setCreatedAt(LocalDateTime.now().format(formatter));
        PostService.addComment(comment);
        return ResponseEntity.ok("Comment added successfully for the Post!");
    }
}
