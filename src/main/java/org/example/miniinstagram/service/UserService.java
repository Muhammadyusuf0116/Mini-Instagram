package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.requestDto.UpdateProfileRequestDTO;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;

import java.util.List;

public interface UserService {

    ProfileResponseDTO getMyProfile();

    void updateMyProfile(UpdateProfileRequestDTO requestDTO);

    ProfileResponseDTO getUserProfile(String username);

    List<ProfileResponseDTO> searchUsers(String keyword);
}
