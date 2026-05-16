package org.example.miniinstagram.controller;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> toggleFollow(
            @PathVariable Long userId
    ) {

        followService.toggleFollow(userId);

        return ResponseEntity.ok("Follow status changed");
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<ProfileResponseDTO>> getFollowers(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                followService.getFollowers(userId)
        );
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<ProfileResponseDTO>> getFollowing(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                followService.getFollowing(userId)
        );
    }

    @GetMapping("/my-followers")
    public ResponseEntity<List<ProfileResponseDTO>> getMyFollowers() {

        return ResponseEntity.ok(
                followService.getMyFollowers()
        );
    }

    @GetMapping("/my-following")
    public ResponseEntity<List<ProfileResponseDTO>> getMyFollowing() {

        return ResponseEntity.ok(
                followService.getMyFollowing()
        );
    }
}