package com.pulse.demo.Modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class UserModal implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private String userid;
    private String username;
    private String password;
    private String email;
    private String name;
    private String profileUrl;

    @ElementCollection
    private Collection<String> prefferedContents = new ArrayList<>();
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private Role role = Role.USER;
    private int likesCount = 0;
    private int followersCount = 0;
    private int followingCount = 0;
    private LocalDateTime createdAt;

    public UserModal(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserModal(LocalDateTime createdAt, String email, int followersCount, int followingCount, int likesCount, String name, String password, String profileUrl, String userid, String username, ArrayList<String> prefferedContents) {
        this.createdAt = createdAt;
        this.email = email;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.likesCount = likesCount;
        this.name = name;
        this.password = password;
        this.profileUrl = profileUrl;
        this.userid = userid;
        this.username = username;
        this.prefferedContents = prefferedContents;
    }

    public UserModal(LocalDateTime createdAt, String email, String name, String password, String profileUrl, String username, ArrayList<String> prefferedContents) {
        this.createdAt = createdAt;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileUrl = profileUrl;
        this.username = username;
        this.prefferedContents = prefferedContents;
    }

    public UserModal(int followersCount, int followingCount, int likesCount, String name, String password, String profileUrl, String username) {
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.likesCount = likesCount;
        this.name = name;
        this.password = password;
        this.profileUrl = profileUrl;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
