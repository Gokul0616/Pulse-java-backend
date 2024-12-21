package com.pulse.demo.Modals;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TweakModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tweakId;
    private String userId;
    private String description;
    private String type;  // Could be "image" or "video"
    private String url;
    private int likesCount = 0;
    private int commentsCount = 0;
    private LocalDateTime createdAt;

    public TweakModal() {
    }

    public TweakModal(String userId, String tweakId, String description, String type, String url, int likesCount, int commentsCount, LocalDateTime createdAt) {
        this.userId = userId;
        this.tweakId = tweakId;
        this.description = description;
        this.type = type;
        this.url = url;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
    }

    public TweakModal(String userId, String description, String type, String url, int likesCount, int commentsCount, LocalDateTime createdAt) {
        this.userId = userId;
        this.description = description;
        this.type = type;
        this.url = url;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
    }
}
