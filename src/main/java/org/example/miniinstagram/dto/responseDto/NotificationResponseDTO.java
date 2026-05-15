package org.example.miniinstagram.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.miniinstagram.enums.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponseDTO {

    private Long id;

    private NotificationType type;

    private boolean isRead;

    private String senderUsername;

    private String senderProfileImage;

    private Long postId;

    private LocalDateTime createdAt;
}