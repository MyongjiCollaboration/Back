package com.example.demo.controller;

import com.example.demo.authentication.user.AuthenticatedUser;
import com.example.demo.dto.AlbumPhotoDto;
import com.example.demo.dto.AlbumRequestDto;
import com.example.demo.dto.AlbumResponseDto;
import com.example.demo.entity.Album;
import com.example.demo.entity.Users;
import com.example.demo.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/album")
@RequiredArgsConstructor
@Tag(name = "앨범", description = "Album API")
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    @Operation(summary = "앨범 생성")
    public String createAlbum(@AuthenticatedUser Users user, @RequestBody AlbumRequestDto albumRequestDto) {
        return albumService.createAlbum(user, albumRequestDto);
    }

    // 페이지네이션 미구현
    @GetMapping("/list")
    @Operation(summary = "앨범 리스트 조회")
    public List<AlbumResponseDto> listAlbums(@AuthenticatedUser Users user) {
        return albumService.getAllAlbums(user);
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "특정 앨범 포토 조회")
    public List<AlbumPhotoDto> getAlbumDetail(@PathVariable UUID id) {
        return albumService.getAlbumDetail(id);
    }

}
