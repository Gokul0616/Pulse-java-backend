package com.pulse.demo.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pulse.demo.Modals.MessageModal;
import com.pulse.demo.Repository.MessageRepository;
@Service
public class MessageServices {
    
    private static MessageRepository messageRepository;
        
            public MessageServices(MessageRepository messageRepository) {
                MessageServices.messageRepository= messageRepository;
        }
    public static void getMessageBySenderId(String id) {
        messageRepository.getMessageBySenderId(id);
    }
    public static void sendMessage(MessageModal message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            message.setSentAt(LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));
       String messageId = UUID.randomUUID().toString();
       message.setMessageId(messageId);
        messageRepository.save(message);
    }
    @Transactional
    public static ResponseEntity<String> deleteMessage(String messageId) {
        messageRepository.deleteMessageByMessageId(messageId);
        return ResponseEntity.ok("Message deleted Successfully!");
    }
}
