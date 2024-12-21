package com.pulse.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.demo.Modals.PostModal;

@Repository
public interface PostRepository extends JpaRepository<PostModal, Long> {

    PostModal findPostByUserId(String userId);

    boolean existsByUserId(String userId);

    PostModal findPostByPostId(String postId);

    List<PostModal> findPostsByTagsIn(List<String> preferredTags);

    boolean existsByPostId(String postId);
}
