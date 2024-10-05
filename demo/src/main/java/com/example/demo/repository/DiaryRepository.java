package com.example.demo.repository;

import com.example.demo.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {
    List<Diary> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
