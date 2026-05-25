package org.example.miniinstagram.mapper;

import org.example.miniinstagram.dto.responseDto.PostResponseDTO;
import org.example.miniinstagram.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${app.base-url}")
    private String baseUrl;

    public PostResponseDTO toResponseDTO(Post post) {

        if (post == null) {
            return null;
        }

        PostResponseDTO dto = new PostResponseDTO();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setImageUrl(baseUrl + "/uploads/posts/" + post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());

        if (post.getUser() != null) {

            dto.setUsername(post.getUser().getUserName());
            dto.setUserProfileImage(post.getUser().getProfileImageUrl());
        }

        dto.setLikesCount(
                post.getLikes() != null
                        ? post.getLikes().size()
                        : 0
        );

        dto.setCommentsCount(
                post.getComment() != null
                        ? post.getComment().size()
                        : 0
        );

        return dto;
    }
}