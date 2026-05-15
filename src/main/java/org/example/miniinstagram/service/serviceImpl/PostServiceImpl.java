package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.CreatePostRequestDTO;
import org.example.miniinstagram.dto.requestDto.UpdatePostRequestDTO;
import org.example.miniinstagram.dto.responseDto.PostResponseDTO;
import org.example.miniinstagram.mapper.PostMapper;
import org.example.miniinstagram.model.Follow;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.FollowRepository;
import org.example.miniinstagram.repository.PostRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostMapper postMapper;

    @Override
    public void createPost(CreatePostRequestDTO requestDTO) {

        User currentUser = getCurrentUser();

        Post post = Post.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .imageUrl(requestDTO.getImageUrl())
                .user(currentUser)
                .build();

        postRepository.save(post);
    }

    @Override
    public void updatePost(Long postId, UpdatePostRequestDTO requestDTO) {

        User currentUser = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You cannot update this post");
        }

        post.setTitle(requestDTO.getTitle());
        post.setDescription(requestDTO.getDescription());
        post.setImageUrl(requestDTO.getImageUrl());

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {

        User currentUser = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You cannot delete this post");
        }

        postRepository.delete(post);
    }

    @Override
    public PostResponseDTO getPostById(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return postMapper.toResponseDTO(post);
    }

    @Override
    public List<PostResponseDTO> getMyPosts() {

        User currentUser = getCurrentUser();

        return postRepository.findByUser(currentUser)
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .map(postMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<PostResponseDTO> getUserPosts(Long userId) {

        return postRepository.findByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .map(postMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<PostResponseDTO> getFeed() {

        User currentUser = getCurrentUser();

        List<User> followingUsers = followRepository
                .findByFollower(currentUser)
                .stream()
                .map(Follow::getFollowing)
                .toList();

        return postRepository.findAll()
                .stream()
                .filter(post ->
                        followingUsers.contains(post.getUser())
                                || post.getUser().getId().equals(currentUser.getId())
                )
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .map(postMapper::toResponseDTO)
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