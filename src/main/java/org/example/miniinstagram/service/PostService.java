package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.requestDto.CreatePostRequestDTO;
import org.example.miniinstagram.dto.requestDto.UpdatePostRequestDTO;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;

import java.io.IOException;
import java.util.List;

public interface PostService {


    PostResponseDTO createPost(CreatePostRequestDTO requestDTO) throws IOException;

    PostResponseDTO updatePost(Long postId, UpdatePostRequestDTO requestDTO) throws IOException;

    String deletePost(Long postId);

    PostResponseDTO getPostById(Long postId);

    List<PostResponseDTO> getMyPosts();

    List<PostResponseDTO> getUserPosts(Long userId);

    List<PostResponseDTO> getFeed();
}