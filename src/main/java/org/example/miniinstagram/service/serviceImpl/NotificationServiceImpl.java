package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.responseDto.NotificationResponseDTO;
import org.example.miniinstagram.mapper.NotificationMapper;
import org.example.miniinstagram.model.Notification;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.NotificationRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.NotificationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationResponseDTO> getMyNotifications() {

        User currentUser = getCurrentUser();

        return notificationRepository
                .findByReceiverOrderByCreatedAtDesc(currentUser)
                .stream()
                .map(notificationMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);
    }

    @Override
    public void markAllAsRead() {

        User currentUser = getCurrentUser();

        List<Notification> notifications =
                notificationRepository
                        .findByReceiverOrderByCreatedAtDesc(currentUser);

        notifications.forEach(notification ->
                notification.setRead(true));

        notificationRepository.saveAll(notifications);
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}