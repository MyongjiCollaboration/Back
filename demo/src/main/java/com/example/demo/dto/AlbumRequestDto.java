package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumRequestDto {
//    String id;
    String name;

    @Builder
    public AlbumRequestDto(String id, String name) {
//        this.id = id;
        this.name = name;
    }
}
