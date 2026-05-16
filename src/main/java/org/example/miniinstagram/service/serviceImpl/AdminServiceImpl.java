package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.enums.Role;
import org.example.miniinstagram.mapper.PostMapper;
import org.example.miniinstagram.mapper.UserMapper;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.PostRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @Override
    public List<ProfileResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {

        return postRepository.findAll()
                .stream()
                .map(postMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deletePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        postRepository.delete(post);
    }

    @Override
    public void banUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setRole(Role.ROLE_BAN);

        userRepository.save(user);
    }

    @Override
    public void unbanUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
    }
}