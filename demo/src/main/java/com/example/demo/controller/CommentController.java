package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

//     모든 코멘트 조회
    @GetMapping("/")
    public ResponseEntity<?> getAllComments() {
        return commentService.getAllComments();
    }

    // ID로 특정 코멘트 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
//        return commentService.getCommentById(id);
//    }

    //특정 Diary에 속한 코멘트 조회
    @GetMapping("/{diaryId}")
    public ResponseEntity<?> getCommentsByDiaryId(@PathVariable UUID diaryId) {
        return commentService.getCommentsByDiaryId(diaryId);
    }


    @PostMapping("/{diaryId}")
    public ResponseEntity<?> addCommentToDiary(@PathVariable UUID diaryId, @RequestBody CommentDTO commentDTO) {
        return commentService.addCommentToDiary(diaryId, commentDTO);
    }

    // 새로운 코멘트 생성
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    // 특정 코멘트 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable UUID id,
                                           @RequestBody CommentDTO commentDTO) {
        return commentService.updateComment(id, commentDTO);
    }

    // 특정 코멘트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        return commentService.deleteComment(id);
    }
}
