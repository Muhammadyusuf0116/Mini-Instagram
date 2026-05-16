package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.requestDto.CreateCommentRequestDTO;
import org.example.miniinstagram.dto.responseDto.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(Long postId, CreateCommentRequestDTO requestDTO);

    String deleteComment(Long commentId);

    List<CommentResponseDTO> getPostComments(Long postId);
}