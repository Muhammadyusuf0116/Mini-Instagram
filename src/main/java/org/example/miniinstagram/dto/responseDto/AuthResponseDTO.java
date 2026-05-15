package org.example.miniinstagram.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.miniinstagram.enums.Role;

@Getter
@Setter
public class AuthResponseDTO {

    private String token;
    private String email;
    private Role role;
}
