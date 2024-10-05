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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public ResponseEntity<List<DiaryDTO>> getAllDiaries() {
        List<Diary> diaries = diaryRepository.findAll();
        List<DiaryDTO> diaryDTOs = diaries.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(diaryDTOs);
    }

    public ResponseEntity<?> getDiaryById(UUID diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        if (diary.isPresent()) {
            DiaryDTO diaryDTO = convertToDTO(diary.get());
            return ResponseEntity.status(HttpStatus.OK).body(diaryDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
    }

    public ResponseEntity<?> getDiaryByCreatedAt(LocalDate createdDate) {
        // 하루의 시작과 끝 시간을 계산
        LocalDateTime startOfDay = createdDate.atStartOfDay();
        LocalDateTime endOfDay = createdDate.atTime(23, 59, 59);

        // 날짜 범위로 다이어리 검색
        List<Diary> diaries = diaryRepository.findByCreatedAtBetween(startOfDay, endOfDay);
        if (!diaries.isEmpty()) {
            List<DiaryDTO> diaryDTOs = diaries.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(diaryDTOs);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No diaries found on date: " + createdDate);
    }

    public ResponseEntity<?> createDiary(DiaryDTO diaryDTO) {
        Diary newDiary = new Diary();
        Diary createdDiary = this.setEntityData(diaryDTO, newDiary);

        Diary savedDiary = diaryRepository.save(createdDiary);
        DiaryDTO savedDiaryDTO = convertToDTO(savedDiary);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiaryDTO);
    }

    public ResponseEntity<?> updateDiary(UUID diaryId, DiaryDTO diaryDTO) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();
            Diary changedDiary = this.setEntityData(diaryDTO, diary);

            Diary savedDiary = diaryRepository.save(changedDiary);
            DiaryDTO savedDiaryDTO = convertToDTO(savedDiary);
            return ResponseEntity.status(HttpStatus.OK).body(savedDiaryDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
        }
    }

    public ResponseEntity<?> deleteDiary(UUID diaryId) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            diaryRepository.deleteById(diaryId);
            return ResponseEntity.status(HttpStatus.OK).body("Diary deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
    }

    // Diary 엔티티를 업데이트하는 메서드
    private Diary setEntityData(DiaryDTO diaryDTO, Diary diary) {
        diary.setTitle(diaryDTO.getTitle());
        diary.setContent(diaryDTO.getContent());
        // createdAt이 null일 경우 현재 시간으로 설정
        if (diary.getCreatedAt() == null) {
            diary.setCreatedAt(LocalDateTime.now());
        }
        return diary;
    }

    // Diary 엔티티를 DiaryDTO로 변환하는 메서드
    private DiaryDTO convertToDTO(Diary diary) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setId(diary.getId());
        diaryDTO.setTitle(diary.getTitle());
        diaryDTO.setContent(diary.getContent());
        diaryDTO.setCreatedAt(diary.getCreatedAt());
//        diaryDTO.setModifiedDate(diary.getModifiedDate());
        return diaryDTO;
    }
}
