package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.mapper.UserMapper;
import org.example.miniinstagram.model.Like;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.LikeRepository;
import org.example.miniinstagram.repository.PostRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.LikeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void toggleLike(Long postId) {

        User currentUser = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Like existingLike = likeRepository
                .findByUserAndPost(currentUser, post)
                .orElse(null);

        if (existingLike != null) {
            likeRepository.delete(existingLike);
        } else {

            Like like = Like.builder()
                    .user(currentUser)
                    .post(post)
                    .build();

            likeRepository.save(like);
        }
    }

    @Override
    public List<ProfileResponseDTO> getPostLikes(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return likeRepository.findByPost(post)
                .stream()
                .map(Like::getUser)
                .map(userMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Long getLikesCount(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return (long) likeRepository.findByPost(post).size();
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}