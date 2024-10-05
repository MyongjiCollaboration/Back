package com.example.demo.controller;

import com.example.demo.dto.AlbumPhotoDto;
import com.example.demo.dto.AlbumPhotoResponseDto;
import com.example.demo.entity.AlbumPhoto;
import com.example.demo.service.AlbumPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albumPhoto")
@Tag(name = "포토앨범", description = "AlbumPhoto API")
public class AlbumPhotoController {

    private final AlbumPhotoService albumPhotoService;

    @PostMapping("/{id}")
    @Operation(summary = "앨범포토 추가")
    public AlbumPhotoResponseDto saveAlbumPhoto(@PathVariable UUID id, @RequestPart MultipartFile file) {

        return albumPhotoService.savePhotoToAlbum(id, file);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "앨범포토 삭제")
    public String deleteAlbumPhoto(@PathVariable UUID id) {
        return albumPhotoService.deleteAlbumPhoto(id);
    }

}
