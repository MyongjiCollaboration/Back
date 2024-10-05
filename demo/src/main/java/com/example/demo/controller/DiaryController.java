package com.example.demo.controller;


import com.example.demo.dto.DiaryDTO;
import com.example.demo.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/")
    public ResponseEntity<?> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getDiaryById(@PathVariable UUID id) {
        return diaryService.getDiaryById(id);
    }

    //2024-10-05식으로 받게 되어 있음
    @GetMapping("/{DiaryCreatedAt}")
    public ResponseEntity<?> getDiaryByCreatedAt(@PathVariable("DiaryCreatedAt") LocalDate createdAt) {
        return diaryService.getDiaryByCreatedAt(createdAt);
    }



    @PostMapping
    public ResponseEntity<?> createDiary(@RequestPart("diary") DiaryDTO diaryDTO,
                                         @RequestPart("files") List<MultipartFile> files) {
        return diaryService.createDiary(diaryDTO, files);
    }

    // 다이어리 업데이트
    @PutMapping("/{diaryId}")
    public ResponseEntity<?> updateDiary(@PathVariable UUID diaryId,
                                         @RequestPart("diary") DiaryDTO diaryDTO,
                                         @RequestPart("files") List<MultipartFile> files) {
        return diaryService.updateDiary(diaryId, diaryDTO, files);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        return diaryService.deleteDiary(id);
    }

}
