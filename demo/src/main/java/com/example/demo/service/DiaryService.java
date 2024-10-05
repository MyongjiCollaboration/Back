package com.example.demo.service;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.entity.Diary;
import com.example.demo.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;


    public ResponseEntity<List<Diary>> getAllDiaries() {
        return ResponseEntity.status(HttpStatus.OK).body(diaryRepository.findAll());
    }

    public ResponseEntity<?> getDiaryById(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        if (diary.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(diary.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
    }

    public ResponseEntity<?> getDiaryByCreatedAt(LocalDate createdDate) {
        // 하루의 시작과 끝 시간을 계산
        LocalDateTime startOfDay = createdDate.atStartOfDay();
        LocalDateTime endOfDay = createdDate.atTime(23, 59, 59);

        // 날짜 범위로 다이어리 검색
        List<Diary> diaries = diaryRepository.findByCreatedAtBetween(startOfDay, endOfDay);
        if (!diaries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(diaries);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No diaries found on date: " + createdDate);
    }

    public ResponseEntity<?> createDiary(DiaryDTO diaryDTO) {
        Diary newDiary = new Diary();
        Diary createdDiary = this.setEntityData(diaryDTO, newDiary);

        Diary savedDiary = diaryRepository.save(createdDiary);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiary);
    }

    public ResponseEntity<?> updateDiary(Long diaryId, DiaryDTO diaryDTO) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();
            Diary changedDiary = this.setEntityData(diaryDTO, diary);

            return ResponseEntity.status(HttpStatus.OK).body(diaryRepository.save(changedDiary));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
        }
    }

    public ResponseEntity<?> deleteDiary(Long diaryId) {
        Optional<Diary> userOptional = diaryRepository.findById(diaryId);
        if (userOptional.isPresent()) {
            diaryRepository.deleteById(diaryId);
            return ResponseEntity.status(HttpStatus.OK).body("Diary deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
    }

    private Diary setEntityData(DiaryDTO diaryDTO, Diary diary) {
//        diary.setId(diaryDTO.getId());
        diary.setTitle(diaryDTO.getTitle());
        diary.setContent(diaryDTO.getContent());
//         createdAt이 null일 경우 현재 시간으로 설정
        if (diary.getCreatedAt() == null) {
            diary.setCreatedAt(LocalDateTime.now());
        }

        return diary;
    }
}
