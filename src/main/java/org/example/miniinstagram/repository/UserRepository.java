package org.example.miniinstagram.repository;

import org.example.miniinstagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByUserName(String username);

    Optional<User> findByUserName(String userName);

    boolean existsByEmail(String email);

    List<User> findByUserNameContainingIgnoreCase(String keyword);

}
