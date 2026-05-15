package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.requestDto.CreatePostRequestDTO;
import org.example.miniinstagram.dto.requestDto.UpdatePostRequestDTO;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;

import java.util.List;

public interface PostService {


    void createPost(CreatePostRequestDTO requestDTO);

    void updatePost(Long postId, UpdatePostRequestDTO requestDTO);

    void deletePost(Long postId);

    PostResponseDTO getPostById(Long postId);

    List<PostResponseDTO> getMyPosts();

    List<PostResponseDTO> getUserPosts(Long userId);

    List<PostResponseDTO> getFeed();
}