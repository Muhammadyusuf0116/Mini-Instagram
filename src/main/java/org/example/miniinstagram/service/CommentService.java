package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.requestDto.CreateCommentRequestDTO;
import org.example.miniinstagram.dto.responseDto.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    void createComment(Long postId, CreateCommentRequestDTO requestDTO);

    void deleteComment(Long commentId);

    List<CommentResponseDTO> getPostComments(Long postId);
}