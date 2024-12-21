package com.pulse.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pulse.demo.Exceptions.UserNotFoundException;
import com.pulse.demo.Modals.Role;
import com.pulse.demo.Modals.SigninRequest;
import com.pulse.demo.Modals.UserModal;
import com.pulse.demo.Repository.UserRepository;
import com.pulse.demo.security.JWTService;

@Service
public class UserService {

    private static UserRepository userRepository = null;
    private static AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        UserService.userRepository = userRepository;
        UserService.authenticationManager = authenticationManager;
    }

    public static List<UserModal> getAllUsers() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        if (userRole == null ? Role.ADMIN.toString() == null : userRole.equals(Role.ADMIN.toString())) {
            return userRepository.findAll();
        }
        throw new IllegalStateException("Unauthorized");
        // return userRepository.findAll();
    }

    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static void registerNewUser(UserModal user) {
        Optional<UserModal> existingUser = userRepository.findByUserid(user.getUserid());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("User ID already taken");
        }
        Optional<UserModal> existingEmailUser = userRepository.findByEmail(user.getEmail());
        if (existingEmailUser.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        String pass = user.getPassword();
        String encodepass = encoder.encode(pass);
        user.setPassword(encodepass);
        userRepository.save(user);
    }

    public static void updateUser(String userId, UserModal updatedUser) {
        Optional<UserModal> existingUserOpt = userRepository.findByUserid(userId);

        if (!existingUserOpt.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        UserModal existingUser = existingUserOpt.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFollowersCount(updatedUser.getFollowersCount());
        existingUser.setFollowingCount(updatedUser.getFollowingCount());
        existingUser.setLikesCount(updatedUser.getLikesCount());
        existingUser.setProfileUrl(updatedUser.getProfileUrl());
        existingUser.setUsername(updatedUser.getUsername());
        userRepository.save(existingUser);
    }

    public static UserModal getUserbyUserId(String userId) {
        Optional<UserModal> existingUserOpt = userRepository.findByUserid(userId);

        if (!existingUserOpt.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        UserModal existingUser = existingUserOpt.get();
        return existingUser;
    }

    public static UserModal Signin(SigninRequest signinRequest) {
        UserModal user = userRepository.findByUsername(signinRequest.getUsername()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else if (user.getUsername().equals(signinRequest.getUsername()) && user.getPassword().equals(signinRequest.getPassword())) {
            return user;
        } else {
            throw new UserNotFoundException("Invalid Credentials");
        }
    }

    public static String verify(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),
                                signinRequest.getPassword()
                        ));
        if (authentication.isAuthenticated()) {
            return JWTService.generateToken(signinRequest.getUsername());
        }
        return "Fail";
    }
}
