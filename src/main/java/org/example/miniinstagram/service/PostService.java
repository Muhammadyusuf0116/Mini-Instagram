package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.requestDto.CreatePostRequestDTO;
import org.example.miniinstagram.dto.requestDto.UpdatePostRequestDTO;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;

import java.util.List;

public interface PostService {


    PostResponseDTO createPost(CreatePostRequestDTO requestDTO);

    PostResponseDTO updatePost(Long postId, UpdatePostRequestDTO requestDTO);

    String deletePost(Long postId);

    PostResponseDTO getPostById(Long postId);

    List<PostResponseDTO> getMyPosts();

    List<PostResponseDTO> getUserPosts(Long userId);

    List<PostResponseDTO> getFeed();
}