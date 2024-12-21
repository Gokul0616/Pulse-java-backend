package com.pulse.demo.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.demo.Exceptions.UserNotFoundException;
import com.pulse.demo.Modals.SigninRequest;
import com.pulse.demo.Modals.UserModal;
import com.pulse.demo.Services.UserService;
import com.pulse.demo.security.JWTService;

@RestController
@RequestMapping(path = "userAuth")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModal user) {
        try {
            String generatedUserId = UUID.randomUUID().toString() + "_T" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            user.setUserid(generatedUserId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            user.setCreatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));
            UserService.registerNewUser(user);
            return ResponseEntity.ok(JWTService.generateToken(user.getUsername()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") String userId,
            @RequestBody UserModal updatedUser) {
        try {
            UserService.updateUser(userId, updatedUser);
            return ResponseEntity.ok("User updated successfully!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/user/{userId}")
    public UserModal getUser(@PathVariable("userId") String userId) {
        try {
            UserModal user = UserService.getUserbyUserId(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            return user;
        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    @GetMapping("/user/getalluser")
    public ResponseEntity<List<UserModal>> getAllUser() {
        List<UserModal> users = UserService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/signin")
    public String getUser(@RequestBody SigninRequest signinRequest) {
        try {
            return UserService.verify(signinRequest);
        } catch (UserNotFoundException e) {
            throw e;
        }
    }
}
