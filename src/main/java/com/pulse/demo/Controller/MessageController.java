package com.pulse.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.demo.Modals.MessageModal;
import com.pulse.demo.Services.MessageServices;

@RestController
@RequestMapping(path = "message")
public class MessageController {

    @GetMapping("/{userId}")
    public ResponseEntity<String> getMessageByUserId(@PathVariable("userId") String id) {
        MessageServices.getMessageBySenderId(id);
        return ResponseEntity.ok("Message sent Successfully!");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageModal message) {
        MessageServices.sendMessage(message);
        return ResponseEntity.ok("Message sent Successfully!");
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable("messageId") String messageId) {
        MessageServices.deleteMessage(messageId);
        return ResponseEntity.ok("Message sent Successfully!");
    }
}
