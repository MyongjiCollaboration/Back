package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class AlbumPhotoResponseDto {

    private UUID id;
    private String url;

    @Builder
    public AlbumPhotoResponseDto(UUID id, String url) {
        this.id = id;
        this.url = url;
    }
}
