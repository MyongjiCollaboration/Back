package com.example.demo.repository;

import com.example.demo.entity.DiaryPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiaryPhotoRepository extends JpaRepository<DiaryPhoto, UUID> {
}
