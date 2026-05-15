package org.example.miniinstagram.service;


import org.example.miniinstagram.dto.requestDto.LoginRequestDTO;
import org.example.miniinstagram.dto.requestDto.RegisterRequestDTO;
import org.example.miniinstagram.dto.responseDto.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);

    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);


}
