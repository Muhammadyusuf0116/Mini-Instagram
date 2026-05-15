package org.example.miniinstagram.mapper;

import org.example.miniinstagram.dto.responseDto.AuthResponseDTO;
import org.example.miniinstagram.model.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponseDTO toResponseDTO(User user, String token){
        if (user == null) return null;

        AuthResponseDTO dto = new AuthResponseDTO();
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setToken(token);

        return dto;
    }

}
