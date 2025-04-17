package com.bos.usertaskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MakeShortUrlInDto {
    @NotBlank(message = "Original URL is required")
    private String originalUrl;
}
