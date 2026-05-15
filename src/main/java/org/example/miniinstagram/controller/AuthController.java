package org.example.miniinstagram.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.LoginRequestDTO;
import org.example.miniinstagram.dto.requestDto.RegisterRequestDTO;
import org.example.miniinstagram.dto.responseDto.AuthResponseDTO;
import org.example.miniinstagram.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@Tag(name = "Auzentifikatsiya qilish uchun ")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "User ro'yhatdan o'tadi!")
    public ResponseEntity<AuthResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(dto));

    }

    @PostMapping("/login")
    @Operation(summary = "User Login qilish uchun!")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

}