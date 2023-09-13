package com.testSpring.contentcalendar.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record Content(
        Integer id,
        @NotBlank(message = "Title is mandatory") String title,
        String description,
        Status status,
        Type contentType,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String url) {
}
