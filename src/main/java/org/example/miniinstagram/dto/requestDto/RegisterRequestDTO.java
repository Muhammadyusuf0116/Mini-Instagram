package org.example.miniinstagram.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must be minimum 6 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;
}
