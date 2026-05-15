package org.example.miniinstagram.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String bio;
    private String profileImage;
    private Integer followersCount;
    private Integer followingCount;
    private LocalDateTime createdAt;


}
