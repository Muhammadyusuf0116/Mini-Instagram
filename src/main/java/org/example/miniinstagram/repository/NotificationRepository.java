package org.example.miniinstagram.repository;

import org.example.miniinstagram.model.Notification;
import org.example.miniinstagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByReceiverOrderByCreatedAtDesc(User receiver);
}