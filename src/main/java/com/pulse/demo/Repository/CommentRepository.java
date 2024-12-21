package com.pulse.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.demo.Modals.CommentModal;

@Repository
public interface CommentRepository extends JpaRepository<CommentModal, Long> {

    }