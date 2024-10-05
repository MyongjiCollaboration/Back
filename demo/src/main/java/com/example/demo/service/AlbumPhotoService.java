package com.example.demo.service;

import com.example.demo.dto.AlbumPhotoDto;
import com.example.demo.dto.AlbumPhotoResponseDto;
import com.example.demo.entity.Album;
import com.example.demo.entity.AlbumPhoto;
import com.example.demo.global.adapter.S3Adapter;
import com.example.demo.repository.AlbumPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumPhotoService {

    private final AlbumPhotoRepository albumPhotoRepository;
    private final AlbumService albumService;
    private final S3Adapter s3Adapter;

    @PostMapping
    public AlbumPhotoResponseDto savePhotoToAlbum(UUID id, @RequestPart MultipartFile file) {
        AlbumPhoto albumPhoto = new AlbumPhoto();
        String url = s3Adapter.uploadImage(file, "album");
        albumPhoto.setUrl(url);

        Album album = albumService.getAlbum(id);
        albumPhoto.setAlbum(album);
        AlbumPhoto savedPhoto = albumPhotoRepository.save(albumPhoto);

        return AlbumPhotoResponseDto.builder()
                .id(savedPhoto.getId())
                .url(savedPhoto.getUrl()).build();
    }


    public String deleteAlbumPhoto(UUID id) {
        AlbumPhoto albumPhoto = findAlbumPhotoById(id);
        s3Adapter.deleteImage(albumPhoto.getUrl());
        albumPhotoRepository.delete(albumPhoto);

        return "해당 앨범포토를 삭제하였습니다.";
    }

    private AlbumPhoto findAlbumPhotoById(UUID id) {
        Optional<AlbumPhoto> albumPhoto = albumPhotoRepository.findById(id);
        if (albumPhoto.isPresent())
            return albumPhoto.get();
        else
            throw new RuntimeException("존재하지 않은 포토앨범 ID 입니다");
    }
}
