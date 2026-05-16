package org.example.miniinstagram.repository;

import org.example.miniinstagram.model.Like;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);

    List<Like> findByPost(Post post);

    Long countByPost(Post post);

    boolean existsByUserAndPost(User user, Post post);
}