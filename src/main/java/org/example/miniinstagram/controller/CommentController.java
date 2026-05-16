package org.example.miniinstagram.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.CreateCommentRequestDTO;
import org.example.miniinstagram.dto.requestDto.UpdateCommentRequestDTO;
import org.example.miniinstagram.dto.responseDto.CommentResponseDTO;
import org.example.miniinstagram.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CreateCommentRequestDTO dto
    ) {
        return ResponseEntity.ok(commentService.createComment(postId, dto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok("Comment deleted successfully");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getPostComments(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(
                commentService.getPostComments(postId)
        );
    }
}