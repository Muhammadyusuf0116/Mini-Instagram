package org.example.miniinstagram.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private String username;

    private String userProfileImage;

    private Integer likesCount;

    private Integer commentsCount;

    private LocalDateTime createdAt;
}