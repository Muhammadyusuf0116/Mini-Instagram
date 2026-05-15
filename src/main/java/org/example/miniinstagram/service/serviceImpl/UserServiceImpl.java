package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.UpdateProfileRequestDTO;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.mapper.UserMapper;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ProfileResponseDTO getMyProfile() {

        User currentUser = getCurrentUser();

        return userMapper.toResponseDTO(currentUser);
    }

    @Override
    public void updateMyProfile(UpdateProfileRequestDTO requestDTO) {

        User currentUser = getCurrentUser();

        currentUser.setFullName(requestDTO.getFullName());
        currentUser.setBio(requestDTO.getBio());
        currentUser.setProfileImageUrl(requestDTO.getImagUrl());

        userRepository.save(currentUser);
    }

    @Override
    public ProfileResponseDTO getUserProfile(String username) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toResponseDTO(user);
    }

    @Override
    public List<ProfileResponseDTO> searchUsers(String keyword) {

        return userRepository
                .findByUserNameContainingIgnoreCase(keyword)
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
