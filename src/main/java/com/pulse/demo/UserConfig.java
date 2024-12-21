package com.pulse.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pulse.demo.Modals.MessageModal;
import com.pulse.demo.Modals.PostModal;
import com.pulse.demo.Modals.TweakModal;
import com.pulse.demo.Modals.UserModal;
import com.pulse.demo.Repository.MessageRepository;
import com.pulse.demo.Repository.PostRepository;
import com.pulse.demo.Repository.TweakReposiitory;
import com.pulse.demo.Repository.UserRepository;

@Configuration
public class UserConfig {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PostRepository postRepository, TweakReposiitory tweakReposiitory,
            MessageRepository messageRepository) {
        return args -> {
            PostModal post1 = new PostModal(
                    "user101",
                    "post001", "Check out my new blog on Spring Boot Security!",
                    "blog", "http://example.com/blog/spring-boot-security",
                    new ArrayList<>(List.of("springboot", "security")),
                    350,
                    45,
                    LocalDateTime.now().minusDays(5)
            );

            PostModal post2 = new PostModal(
                    "user102",
                    "post002", "Excited to share my latest art project 🎨!",
                    "image",
                    "http://example.com/images/artwork.jpg",
                    new ArrayList<>(List.of("art", "painting", "image")),
                    1200,
                    89,
                    LocalDateTime.now().minusDays(3)
            );

            PostModal post3 = new PostModal(
                    "user103",
                    "post003", "Check out this incredible landscape shot I captured! 📷",
                    "image",
                    "http://example.com/images/landscape.jpg",
                    new ArrayList<>(List.of("landscape", "nature")),
                    500,
                    60,
                    LocalDateTime.now().minusHours(12)
            );

            // Create UserModal objects
            UserModal alice = new UserModal(
                    LocalDateTime.now().minusDays(10),
                    "alice123@gmail.com",
                    0,
                    0,
                    0,
                    "Alice",
                    "Alice@123",
                    "https://example.com/profiles/alice.jpg",
                    "Alice001",
                    "alice123",
                    new ArrayList<>(List.of("blog", "springboot"))
            );

            UserModal bob = new UserModal(
                    LocalDateTime.now().minusDays(20),
                    "bob_rock@gmail.com",
                    0,
                    0,
                    0,
                    "Bob",
                    "Bob@2023",
                    "https://example.com/profiles/bob.jpg",
                    "Bob001",
                    "bob_rock",
                    new ArrayList<>(List.of("image", "art"))
            );

            UserModal charlie = new UserModal(
                    LocalDateTime.now().minusDays(30),
                    "charlie.zen@gmail.com",
                    0,
                    0,
                    0,
                    "Charlie",
                    "$2a$12$dwC1JMT/3FxLsoqhvnMGwuaDYzrOdQmLXJc6hlazFf1OjwUMwdQpu",
                    "https://example.com/profiles/charlie.jpg",
                    "Charlie001",
                    "charliezen",
                    new ArrayList<>(List.of("image", "landscape"))
            );

            UserModal diana = new UserModal(
                    LocalDateTime.now().minusDays(40),
                    "diana.quill@gmail.com",
                    0,
                    0,
                    0,
                    "Diana",
                    "Diana@789",
                    "https://example.com/profiles/diana.jpg",
                    "Diana001",
                    "dianaquill",
                    new ArrayList<>(List.of("landscape", "nature"))
            );
            TweakModal tweak1 = new TweakModal(
                    "user201",
                    "Just completed my 100th coding challenge! 🎉",
                    "text",
                    "http://example.com/challenge-complete",
                    250,
                    40,
                    LocalDateTime.now().minusDays(1)
            );

            TweakModal tweak2 = new TweakModal(
                    "user202",
                    "Learning Docker has been a game-changer for my dev workflow! 🚀",
                    "image",
                    "http://example.com/images/docker-workflow.jpg",
                    400,
                    75,
                    LocalDateTime.now().minusDays(2)
            );

            TweakModal tweak3 = new TweakModal(
                    "user203",
                    "New video is live! Learn how to master design patterns in Java.",
                    "video",
                    "http://example.com/videos/design-patterns.mp4",
                    700,
                    120,
                    LocalDateTime.now().minusDays(7)
            );

            TweakModal tweak4 = new TweakModal(
                    "user204",
                    "Just reached 500 followers! Thanks to everyone for the support! 🙌",
                    "text",
                    "http://example.com/followers-update",
                    600,
                    100,
                    LocalDateTime.now().minusHours(8)
            );

            MessageModal message1 = new MessageModal(
                    "alice123",
                    "bob_rock",
                    "Hey Bob, did you check out the new Spring Boot course?",
                    LocalDateTime.now().minusDays(1),
                    false,
                    "text"
            );

            MessageModal message2 = new MessageModal(
                    "bob_rock",
                    "charlie.zen",
                    "Hey Charlie, can you review my new portfolio website?",
                    LocalDateTime.now().minusDays(2),
                    false,
                    "text"
            );

            MessageModal message3 = new MessageModal(
                    "diana.quill",
                    "alice123",
                    "Hi Alice, are you free for a quick meeting tomorrow?",
                    LocalDateTime.now().minusHours(6),
                    false,
                    "text"
            );

            MessageModal message4 = new MessageModal(
                    "charlie.zen",
                    "diana.quill",
                    "Sure! I'll prepare some notes for our discussion.",
                    LocalDateTime.now().minusHours(2),
                    false,
                    "text"
            );

            userRepository.saveAll(List.of(alice, bob, charlie, diana));
            postRepository.saveAll(List.of(post1, post2, post3));
            tweakReposiitory.saveAll(List.of(tweak1, tweak2, tweak3, tweak4));
            messageRepository.saveAll(List.of(message1, message2, message3, message4));
        };

    }

}
