package com.pulse.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.demo.Modals.PostModal;
import com.pulse.demo.Repository.PostRepository;
import com.pulse.demo.Repository.UserRepository;

@RestController
@RequestMapping(path = "/recommendation")
public class RecommendationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<PostModal> recommendation() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String username = (authentication != null) ? authentication.getName() : null;

        if (username == null) {
            throw new IllegalArgumentException("Username not found in security context.");
        }
        List<String> preferredContents = userRepository
                .findByUsername(username)
                .map(user -> new ArrayList<>(user.getPrefferedContents()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<PostModal> recommendedPosts = postRepository.findPostsByTagsIn(preferredContents);

        return recommendedPosts;
    }
}
