package org.example.miniinstagram.mapper;

import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public ProfileResponseDTO toResponseDTO(User user){
        if (user == null) return null;

        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(user.getId());
        dto.setBio(user.getBio());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUserName());
        dto.setFullName(user.getFullName());
        dto.setProfileImage(user.getProfileImageUrl());
        dto.setCreatedAt(user.getCreatedAt());

        dto.setFollowersCount(
                user.getFollowers() != null
                        ? user.getFollowers().size()
                        : 0
        );

        dto.setFollowingCount(
                user.getFollowing() != null
                        ? user.getFollowing().size()
                        : 0
        );
        return dto;
    }
}
