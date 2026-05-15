package org.example.miniinstagram.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {

    private Long id;

    private String content;

    private Long userId;

    private String username;

    private Long postId;

    private LocalDateTime createdAt;
}