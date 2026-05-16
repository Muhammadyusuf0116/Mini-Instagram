package org.example.miniinstagram.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.CreatePostRequestDTO;
import org.example.miniinstagram.dto.requestDto.UpdatePostRequestDTO;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;
import org.example.miniinstagram.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<PostResponseDTO> createPost(
            @Valid @RequestBody CreatePostRequestDTO dto
            ){
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @PutMapping("{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable Long postId, @Valid @RequestBody UpdatePostRequestDTO dto
            ){
        return ResponseEntity.ok(postService.updatePost(postId, dto));
    }

    @DeleteMapping("postId")
    public ResponseEntity<String> deletePost(
            @PathVariable Long postId){
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok( postService.getPostById(postId));
    }

    @GetMapping("me")
    public ResponseEntity<List<PostResponseDTO>> getMyPosts(){
        return ResponseEntity.ok(postService.getMyPosts());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostResponseDTO>> getUserPosts(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(postService.getUserPosts(userId));
    }

    @GetMapping("feed")
    public ResponseEntity<List<PostResponseDTO>> getFeed(){
        return ResponseEntity.ok(postService.getFeed());
    }


}
