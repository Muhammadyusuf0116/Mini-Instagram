package org.example.miniinstagram.service;

import org.example.miniinstagram.dto.responseDto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    List<NotificationResponseDTO> getMyNotifications();

    void markAsRead(Long notificationId);

    void markAllAsRead();

}