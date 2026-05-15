package org.example.miniinstagram.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDTO {

    private String bio;

    private String imagUrl;

    private String fullName;
}
