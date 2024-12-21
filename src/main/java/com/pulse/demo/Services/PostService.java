package com.pulse.demo.Services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pulse.demo.Modals.CommentModal;
import com.pulse.demo.Modals.PostModal;
import com.pulse.demo.Repository.CommentRepository;
import com.pulse.demo.Repository.PostRepository;

@Service
public class PostService {

    private static PostRepository postRepository;
    private static CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        PostService.commentRepository = commentRepository;
        PostService.postRepository = postRepository;
    }

    public static void deletePost(Long postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
        } else {
            throw new RuntimeException("Post not found with id " + postId);
        }
    }

    public static PostModal getPostByPostId(String postId) {
        try {
            boolean exists = postRepository.existsByPostId(postId);
            if (!exists) {
                throw new IllegalStateException("Post does not exist");
            }
            PostModal post = postRepository.findPostByPostId(postId);
            return post;
        } catch (Exception e) {
            throw new IllegalStateException("Post does not exist");
        }

    }

    public static PostModal getPostByUserId(String userId) {
        boolean exists = postRepository.existsByUserId(userId);
        if (!exists) {
            throw new IllegalStateException("Post with userId " + userId + " does not exist");
        } else {
            return postRepository.findPostByUserId(userId);
        }
    }

    public static void addNewPost(PostModal post) {
        String postId = UUID.randomUUID().toString();
        post.setPostId(postId);
        postRepository.save(post);
    }

    public static void addLike(String postId) {
        PostModal post = postRepository.findPostByPostId(postId);
        if (post != null) {
            post.setLikesCount(post.getLikesCount() + 1);
            postRepository.save(post);
        } else {
            throw new IllegalStateException("Error at adding like on post!");
        }
    }

    public static void addComment(CommentModal comment) {
        String commentId = UUID.randomUUID().toString();
        comment.setCommentId(commentId);
        commentRepository.save(comment);
    }
}
