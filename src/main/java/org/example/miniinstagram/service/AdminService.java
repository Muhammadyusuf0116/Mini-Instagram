package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.responseDto.PostResponseDTO;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;

import java.util.List;

public interface AdminService {

    List<ProfileResponseDTO> getAllUsers();

    void deleteUser(Long userId);

    List<PostResponseDTO> getAllPosts();

    void deletePost(Long postId);

    void banUser(Long userId);

    void unbanUser(Long userId);
}