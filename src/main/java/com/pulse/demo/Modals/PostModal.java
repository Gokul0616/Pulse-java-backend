package com.pulse.demo.Modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postId;
    private String userId;
    private String description;
    private String type;
    private String url;
    private int likesCount = 0;

    @ElementCollection
    private Collection<String> tags = new ArrayList<>();

    private int commentsCount = 0;
    private LocalDateTime createdAt;

    public PostModal(String userId, String postId, String description, String type, String url, ArrayList<String> tags, int likesCount, int commentsCount, LocalDateTime createdAt) {
        this.userId = userId;
        this.postId = postId;
        this.description = description;
        this.type = type;
        this.url = url;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.tags = tags;
    }

    public PostModal(String userId, String description, String type, String url, ArrayList<String> tags, int likesCount, int commentsCount, LocalDateTime createdAt) {
        this.userId = userId;
        this.description = description;
        this.type = type;
        this.url = url;
        this.tags = tags;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PostModal{"
                + "postId=" + postId
                + ", userId='" + userId + '\''
                + ", description='" + description + '\''
                + ", type='" + type + '\''
                + ", url='" + url + '\''
                + ", tags=" + tags
                + ", likesCount=" + likesCount
                + ", commentsCount=" + commentsCount
                + ", createdAt=" + createdAt
                + '}';
    }
}
