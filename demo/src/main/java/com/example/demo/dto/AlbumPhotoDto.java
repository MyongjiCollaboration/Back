package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class AlbumPhotoDto {
    private UUID id;
    private String url;
//    private LocalDateTime createdAt;

    @Builder
    public AlbumPhotoDto(UUID id, String url) {
        this.id = id;
        this.url = url;
//        this.createdAt = createdAt;
    }
}
