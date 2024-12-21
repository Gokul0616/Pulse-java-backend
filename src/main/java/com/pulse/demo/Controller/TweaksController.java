package com.pulse.demo.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.demo.Modals.CommentModal;
import com.pulse.demo.Modals.TweakModal;
import com.pulse.demo.Services.TweakServices;

@RequestMapping(path = "tweak")
@RestController
public class TweaksController {

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewTweak(@RequestBody TweakModal tweak) {
        TweakServices.addNewTweak(tweak);
        return ResponseEntity.ok("Tweak added successfully!");
    }

    @PutMapping("/addlike/{tweakId}")
    public ResponseEntity<String> addTweakLike(@PathVariable("tweakId") String tweakId) {
        TweakServices.addLike(tweakId);
        return ResponseEntity.ok("Like added successfully for the Tweak!");
    }

    @PutMapping("/addComment")
    public ResponseEntity<String> addTweakComment(@RequestBody CommentModal comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        comment.setCreatedAt(LocalDateTime.now().format(formatter));
        TweakServices.addComment(comment);
        return ResponseEntity.ok("Comment added successfully for the Tweak!");
    }

    @GetMapping("/getTweak/{tweakId}")
    public TweakModal getTweakById(@PathVariable("tweakId") String tweakId) {
        return TweakServices.getTweakByTweakId(tweakId);
    }
}
