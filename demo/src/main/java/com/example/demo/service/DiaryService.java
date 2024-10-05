package com.example.demo.service;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.dto.DiaryPhotoResponseDTO;
import com.example.demo.entity.Diary;
import com.example.demo.entity.DiaryPhoto;
import com.example.demo.global.adapter.S3Adapter;
import com.example.demo.repository.DiaryPhotoRepository;
import com.example.demo.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryPhotoRepository diaryPhotoRepository;
    private final S3Adapter s3Adapter; // S3 업로드를 위해 추가

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
//            return ResponseEntity.status(HttpStatus.OK).body(diary);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
    }

    public Optional<Diary> findDiaryEntityById(UUID diaryId) {
        return diaryRepository.findById(diaryId);
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

    public ResponseEntity<?> createDiary(DiaryDTO diaryDTO, List<MultipartFile> files) {
        Diary newDiary = new Diary();
        newDiary.setPhotos(new ArrayList<>()); // 빈 리스트로 초기화

        Diary createdDiary = this.setEntityData(diaryDTO, newDiary);

        // 다이어리 저장
        Diary savedDiary = diaryRepository.save(createdDiary);

        // 사진 저장
        if (files != null && !files.isEmpty()) {
            savePhotosToDiary(savedDiary, files);
            // 사진이 저장된 후 다이어리를 다시 조회하여 photos 리스트 업데이트
            savedDiary = diaryRepository.findById(savedDiary.getId()).orElse(savedDiary);
        }

        DiaryDTO savedDiaryDTO = convertToDTO(savedDiary);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiaryDTO);
    }


    public ResponseEntity<?> updateDiary(UUID diaryId, DiaryDTO diaryDTO, List<MultipartFile> files) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();
            Diary changedDiary = this.setEntityData(diaryDTO, diary);

            // 다이어리 저장
            Diary savedDiary = diaryRepository.save(changedDiary);

            // 사진 저장
            if (files != null && !files.isEmpty()) {
                savePhotosToDiary(savedDiary, files);
            }

            DiaryDTO savedDiaryDTO = convertToDTO(savedDiary);
            return ResponseEntity.status(HttpStatus.OK).body(savedDiaryDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
        }
    }

    public ResponseEntity<?> deleteDiary(UUID diaryId) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();

            // 다이어리와 연관된 모든 사진 삭제
            diary.getPhotos().forEach(photo -> {
                s3Adapter.deleteImage(photo.getUrl()); // S3에서 사진 삭제
                diaryPhotoRepository.delete(photo); // DB에서 사진 삭제
            });

            // 다이어리 삭제
            diaryRepository.deleteById(diaryId);
            return ResponseEntity.status(HttpStatus.OK).body("Diary deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
    }

    // 다이어리에 사진 추가
    private void savePhotosToDiary(Diary diary, List<MultipartFile> files) {
        files.forEach(file -> {
            DiaryPhoto diaryPhoto = new DiaryPhoto();
            String url = s3Adapter.uploadImage(file, "diary");
            diaryPhoto.setUrl(url);
            diaryPhoto.setDiary(diary);
            diaryPhotoRepository.save(diaryPhoto);
        });
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

        // DiaryPhoto 정보를 DiaryPhotoResponseDTO로 변환하여 설정
        List<DiaryPhotoResponseDTO> photoDTOs = diary.getPhotos().stream()
                .map(photo -> DiaryPhotoResponseDTO.builder()
                        .id(photo.getId())
                        .url(photo.getUrl())
                        .build())
                .collect(Collectors.toList());

        diaryDTO.setPhotos(photoDTOs); // 사진 정보 추가
        return diaryDTO;
    }

}
