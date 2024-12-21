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
public class MessageModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String messageId;
    private String senderId;
    private String recipientId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private String messageType;

    public MessageModal() {
    }

    public MessageModal(String messageId, String senderId, String recipientId, String content, LocalDateTime sentAt, boolean isRead, String messageType) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.messageType = messageType;
    }

    public MessageModal(String senderId, String recipientId, String content, LocalDateTime sentAt, boolean isRead, String messageType) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "MessageModal{"
                + "messageId='" + messageId + '\''
                + ", senderId='" + senderId + '\''
                + ", recipientId='" + recipientId + '\''
                + ", content='" + content + '\''
                + ", sentAt=" + sentAt
                + ", isRead=" + isRead
                + ", messageType='" + messageType + '\''
                + '}';
    }
}
