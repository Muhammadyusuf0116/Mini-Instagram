package org.example.miniinstagram.controller;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long postId
    ) {
        likeService.toggleLike(postId);

        return ResponseEntity.ok("Like status changed");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<ProfileResponseDTO>> getPostLikes(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(
                likeService.getPostLikes(postId)
        );
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> getLikesCount(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(
                likeService.getLikesCount(postId)
        );
    }
}