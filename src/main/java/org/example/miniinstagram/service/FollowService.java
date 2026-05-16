package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;

import java.util.List;

public interface FollowService {

    void toggleFollow(Long userId);

    List<ProfileResponseDTO> getFollowers(Long userId);

    List<ProfileResponseDTO> getFollowing(Long userId);

    List<ProfileResponseDTO> getMyFollowing();

    List<ProfileResponseDTO> getMyFollowers();
}