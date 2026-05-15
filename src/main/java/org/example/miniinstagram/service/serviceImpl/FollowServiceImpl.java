package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.mapper.UserMapper;
import org.example.miniinstagram.model.Follow;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.FollowRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.FollowService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void toggleFollow(Long userId) {

        User currentUser = getCurrentUser();

        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (currentUser.getId().equals(targetUser.getId())) {
            throw new RuntimeException("You cannot follow yourself");
        }

        boolean alreadyFollowed = followRepository
                .existsByFollowerAndFollowing(currentUser, targetUser);

        if (alreadyFollowed) {

            followRepository.deleteByFollowerAndFollowing(currentUser, targetUser);

        } else {

            Follow follow = new Follow();

            follow.setFollower(currentUser);
            follow.setFollowing(targetUser);

            followRepository.save(follow);
        }
    }

    @Override
    public List<ProfileResponseDTO> getFollowers(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return followRepository.findByFollowing(user)
                .stream()
                .map(Follow::getFollower)
                .map(userMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProfileResponseDTO> getFollowing(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return followRepository.findByFollower(user)
                .stream()
                .map(Follow::getFollowing)
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