package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.LoginRequestDTO;
import org.example.miniinstagram.dto.requestDto.RegisterRequestDTO;
import org.example.miniinstagram.dto.responseDto.AuthResponseDTO;
import org.example.miniinstagram.enums.Role;
import org.example.miniinstagram.excaption.ResourceNotFoundException;
import org.example.miniinstagram.mapper.AuthMapper;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.security.JwtUtil;
import org.example.miniinstagram.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Bu user allaqachon mavjud: "+ dto.getEmail());
        }
        if(userRepository.existsByUserName(dto.getUsername())){
            throw new IllegalArgumentException("Bu user allaqachon mavjud: "+ dto.getUsername());
        }

        User user = new User();
        user.setUserName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());


        return authMapper.toResponseDTO(userRepository.save(user), token);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Foydalanuvchi topilmadi!"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Parol noto'g'ri");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return authMapper.toResponseDTO(user, token);
    }
}
