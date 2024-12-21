package com.pulse.demo.Modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommentModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postId;
    private String userId;
    private String commentId;
    private String commentText;
    private String createdAt;
    private int likesCount = 0;
    private int repliesCount = 0;

    public CommentModal() {
    }

    public CommentModal(String postId, String userId, String commentText, String createdAt, int likesCount, int repliesCount) {
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.createdAt = createdAt;
        this.likesCount = likesCount;
        this.repliesCount = repliesCount;
    }

    @Override
    public String toString() {
        return "CommentModal{"
                + "id=" + id
                + ", postId='" + postId + '\''
                + ", userId='" + userId + '\''
                + ", commentId='" + commentId + '\''
                + ", commentText='" + commentText + '\''
                + ", createdAt=" + createdAt
                + ", likesCount=" + likesCount
                + ", repliesCount=" + repliesCount
                + '}';
    }
}
