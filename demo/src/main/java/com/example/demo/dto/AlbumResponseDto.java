package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class AlbumResponseDto {

    private UUID id;
    private String name;

    @Builder
    public AlbumResponseDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
