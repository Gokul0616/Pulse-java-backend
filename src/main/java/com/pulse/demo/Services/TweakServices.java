package com.pulse.demo.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pulse.demo.Modals.CommentModal;
import com.pulse.demo.Modals.TweakModal;
import com.pulse.demo.Repository.CommentRepository;
import com.pulse.demo.Repository.TweakReposiitory;

@Service
public class TweakServices {

    private static TweakReposiitory tweakRepository;
    private static CommentRepository commentRepository;

    public TweakServices(TweakReposiitory tweakRepository, CommentRepository commentRepository) {
        TweakServices.tweakRepository = tweakRepository;
        TweakServices.commentRepository = commentRepository;
    }

    public static void addNewTweak(TweakModal tweak) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            tweak.setCreatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));

            String tewakId = UUID.randomUUID().toString();
            tweak.setTweakId(tewakId);
            tweakRepository.save(tweak);
        } catch (Exception e) {
            throw new IllegalStateException("Error At adding Tweak");
        }
    }

    public static void addLike(String tweakId) {
        Optional<TweakModal> existingTweakOpt = tweakRepository.findByTweakId(tweakId);
        if (!existingTweakOpt.isPresent()) {
            throw new IllegalStateException("Tweak not found");
        }
        TweakModal tweak = existingTweakOpt.get();
        tweak.setLikesCount(tweak.getLikesCount() + 1);
        tweakRepository.save(tweak);
    }

    public static void addComment(CommentModal comment) {
        String commentId = UUID.randomUUID().toString();
        comment.setCommentId(commentId);
        commentRepository.save(comment);
    }

    public static TweakModal getTweakByTweakId(String tweakId) {
        TweakModal tweak = tweakRepository.findByTweakId(tweakId).get();
        if (tweak != null) {
            return tweak;
        } else {
            throw new IllegalStateException("Tweak not found");
        }
    }

}
