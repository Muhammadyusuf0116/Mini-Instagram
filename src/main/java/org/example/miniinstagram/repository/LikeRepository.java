package org.example.miniinstagram.repository;

import org.example.miniinstagram.model.Like;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);

    long countByPost(Post post);
}