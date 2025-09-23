package com.example.Task_6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookDTO(
        @NotBlank(message = "Title Can't be Blank")
        @Size(min = 3, max = 50)
        String title,
        @NotBlank(message = "Author Can't be Blank")
        @Size(min = 3, max = 50)
        String author
) {}
