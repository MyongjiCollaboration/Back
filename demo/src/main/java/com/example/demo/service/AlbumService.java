package com.example.demo.service;

import com.example.demo.dto.AlbumPhotoDto;
import com.example.demo.dto.AlbumRequestDto;
import com.example.demo.dto.AlbumResponseDto;
import com.example.demo.entity.Album;
import com.example.demo.entity.AlbumPhoto;
import com.example.demo.entity.Family;
import com.example.demo.entity.Users;
import com.example.demo.repository.AlbumPhotoRepository;
import com.example.demo.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumPhotoRepository albumPhotoRepository;

    // 앨범 생성
    public String createAlbum(AlbumRequestDto albumRequestDto, Users user) {
        Album album = new Album();
        album.setName(albumRequestDto.getName());
//        Family family = user.getFamily();
//        album.setFamily(family);

        albumRepository.save(album);
        return "앨범이 추가되었습니다";
    }

    // 앨범 사진 반환
    public List<AlbumPhotoDto> getAlbumDetail(UUID id) {
        Album album = getAlbum(id);
        List<AlbumPhoto> albumPhotos = albumPhotoRepository.findByAlbum(album);
        List<AlbumPhotoDto> albumPhotoDtoList = new ArrayList<>();
        for(AlbumPhoto photo : albumPhotos) {
            AlbumPhotoDto albumResponseDto = converToAlbumPhotoDto(photo);
            albumPhotoDtoList.add(albumResponseDto);
        }
        return albumPhotoDtoList;
    }

    // 앨범 ID로 특정 앨범 찾기
    public Album getAlbum(UUID id) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if(optionalAlbum.isPresent())
            return optionalAlbum.get();
        else
            throw new RuntimeException("해당 ID의 앨범이 없습니다");
    }

    // 앨범 -> 앨범 DTO
    public AlbumPhotoDto converToAlbumPhotoDto(AlbumPhoto photo) {
        return AlbumPhotoDto.builder()
                .id(photo.getId())
                .url(photo.getUrl())
//                .createdAt(album.getCreatedAt())
                .build();
    }

    public List<AlbumResponseDto> getAllAlbums(Users user) {
        List<Album> albums = albumRepository.findAll();
        List<AlbumResponseDto> albumResponseDtoList = new ArrayList<>();
        for(Album album : albums) {
            AlbumResponseDto albumResponseDto = AlbumResponseDto.builder()
                    .id(album.getId())
                    .name(album.getName())
                    .build();
            albumResponseDtoList.add(albumResponseDto);
        }
        return albumResponseDtoList;
    }


}
