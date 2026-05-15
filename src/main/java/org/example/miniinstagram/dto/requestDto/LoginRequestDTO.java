package org.example.miniinstagram.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is not be blank")
    @Size(min = 6, message = "Password must be minimum 6  characters")
    private String password;
}
