package org.example.miniinstagram.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.UpdateProfileRequestDTO;
import org.example.miniinstagram.dto.responseDto.ProfileResponseDTO;
import org.example.miniinstagram.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "User Rest Api!")
public class UserController {

    private final UserService userService;

    @GetMapping("me")
    public ResponseEntity<ProfileResponseDTO> getProfile(){
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @PutMapping("update")
    public ResponseEntity<String> updateProfile(
            @Valid @RequestBody UpdateProfileRequestDTO dto){
        userService.updateMyProfile(dto);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @PostMapping("username")
    public ResponseEntity<ProfileResponseDTO> getUserProfile(
            @RequestParam String userName
    ){
        return ResponseEntity.ok(userService.getUserProfile(userName));
    }

    @PostMapping("search")
    public ResponseEntity<List<ProfileResponseDTO>> searchUser(
            @RequestParam String keyword
    ){
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }



}