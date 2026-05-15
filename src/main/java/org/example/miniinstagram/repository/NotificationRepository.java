package org.example.miniinstagram.repository;

import org.example.miniinstagram.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
