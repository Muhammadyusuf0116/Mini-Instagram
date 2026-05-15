package org.example.miniinstagram.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;
}
