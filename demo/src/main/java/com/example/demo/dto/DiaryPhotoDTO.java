package com.example.demo.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class DiaryPhotoDTO {
    private UUID id;
    private String url;
    private LocalDateTime createdAt;

    @Builder
    public DiaryPhotoDTO(UUID id, String url) {
        this.id = id;
        this.url = url;
    }
}
