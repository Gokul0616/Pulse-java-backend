package com.pulse.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.demo.Modals.TweakModal;


@Repository
public interface TweakReposiitory extends JpaRepository<TweakModal, Long> {

    Optional<TweakModal> findByTweakId(String tweakId);
      
}
