package com.example.demo.repository;

import com.example.demo.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
