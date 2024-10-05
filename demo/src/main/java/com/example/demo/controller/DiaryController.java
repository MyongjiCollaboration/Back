package com.example.demo.controller;


import com.example.demo.dto.DiaryDTO;
import com.example.demo.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public ResponseEntity<?> getDiaryById(@PathVariable Long id) {
        return diaryService.getDiaryById(id);
    }

    //2024-10-05식 String으로 받게 되어 있음
    @GetMapping("/{DiaryCreatedAt}")
    public ResponseEntity<?> getDiaryByCreatedAt(@PathVariable("DiaryCreatedAt") LocalDate createdAt) {
        return diaryService.getDiaryByCreatedAt(createdAt);
    }

    @PostMapping
    public ResponseEntity<?> createDiary(@RequestBody DiaryDTO diaryDTO){ return diaryService.createDiary(diaryDTO);}

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody DiaryDTO diaryDTO) {
        return diaryService.updateDiary(id, diaryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return diaryService.deleteDiary(id);
    }

}
