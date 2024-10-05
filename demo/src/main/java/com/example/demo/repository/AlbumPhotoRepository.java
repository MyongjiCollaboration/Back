package com.example.demo.repository;

import com.example.demo.entity.Album;
import com.example.demo.entity.AlbumPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlbumPhotoRepository extends JpaRepository<AlbumPhoto, UUID> {

    List<AlbumPhoto> findByAlbum(Album album);

}
