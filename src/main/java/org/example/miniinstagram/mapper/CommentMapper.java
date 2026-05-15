package org.example.miniinstagram.mapper;

import org.example.miniinstagram.dto.responseDto.CommentResponseDTO;
import org.example.miniinstagram.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDTO toResponseDTO(Comment comment) {

        if (comment == null) {
            return null;
        }

        CommentResponseDTO dto = new CommentResponseDTO();

        dto.setId(comment.getId());
        dto.setContent(comment.getText());

        dto.setUserId(comment.getUser().getId());
        dto.setUsername(comment.getUser().getUserName());

        dto.setPostId(comment.getPost().getId());

        dto.setCreatedAt(comment.getCreatedAt());

        return dto;
    }
}