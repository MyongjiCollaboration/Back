package com.example.demo.repository;

import com.example.demo.entity.Album;
import com.example.demo.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    List<Album> findByFamily(Family family);
}
