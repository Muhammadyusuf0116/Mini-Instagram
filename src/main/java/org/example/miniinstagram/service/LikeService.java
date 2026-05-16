package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;

import java.util.List;

public interface LikeService {

    void toggleLike(Long postId);

    List<ProfileResponseDTO> getPostLikes(Long postId);

    Long getLikesCount(Long postId);

}