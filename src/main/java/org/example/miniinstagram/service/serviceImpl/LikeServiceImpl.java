package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.model.Like;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.LikeRepository;
import org.example.miniinstagram.repository.PostRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.LikeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void toggleLike(Long postId) {

        User currentUser = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        boolean alreadyLiked = likeRepository
                .existsByUserAndPost(currentUser, post);

        if (alreadyLiked) {

            likeRepository.deleteByUserAndPost(currentUser, post);

        } else {

            Like like = new Like();

            like.setUser(currentUser);
            like.setPost(post);

            likeRepository.save(like);
        }
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}