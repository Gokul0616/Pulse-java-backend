package com.pulse.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.demo.Modals.UserModal;


public interface UserRepository extends JpaRepository<UserModal, Long> {
    Optional<UserModal> findByEmail(String email);
    Optional<UserModal> findByUserid(String userid);  // Change this line

    Optional<UserModal> findByUsername(String username);
}