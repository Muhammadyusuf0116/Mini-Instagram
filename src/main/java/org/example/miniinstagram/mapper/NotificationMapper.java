package org.example.miniinstagram.mapper;

import org.example.miniinstagram.dto.responseDto.NotificationResponseDTO;
import org.example.miniinstagram.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponseDTO toResponseDTO(Notification notification) {

        NotificationResponseDTO dto = new NotificationResponseDTO();

        dto.setId(notification.getId());
        dto.setType(notification.getType());
        dto.setRead(notification.isRead());

        dto.setSenderUsername(
                notification.getSender().getUserName()
        );

        dto.setSenderProfileImage(
                notification.getSender().getProfileImageUrl()
        );

        if (notification.getPost() != null) {
            dto.setPostId(notification.getPost().getId());
        }

        dto.setCreatedAt(notification.getCreatedAt());

        return dto;
    }
}