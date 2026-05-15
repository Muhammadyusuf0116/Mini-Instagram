package org.example.miniinstagram.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequestDTO {

    @NotBlank(message = "Content is required")
    private String content;
}