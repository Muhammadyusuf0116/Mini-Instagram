package org.example.miniinstagram.controller;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<ProfileResponseDTO>> getAllUsers() {

        return ResponseEntity.ok(
                adminService.getAllUsers()
        );
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId
    ) {

        adminService.deleteUser(userId);

        return ResponseEntity.ok(
                "User deleted successfully"
        );
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {

        return ResponseEntity.ok(
                adminService.getAllPosts()
        );
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long postId
    ) {

        adminService.deletePost(postId);

        return ResponseEntity.ok(
                "Post deleted successfully"
        );
    }

    @PatchMapping("/users/{userId}/ban")
    public ResponseEntity<String> banUser(
            @PathVariable Long userId
    ) {

        adminService.banUser(userId);

        return ResponseEntity.ok(
                "User banned successfully"
        );
    }

    @PatchMapping("/users/{userId}/unban")
    public ResponseEntity<String> unbanUser(
            @PathVariable Long userId
    ) {

        adminService.unbanUser(userId);

        return ResponseEntity.ok(
                "User unbanned successfully"
        );
    }
}