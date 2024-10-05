package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Diary;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DiaryRepository diaryRepository;

    public ResponseEntity<List<CommentDTO>> getAllComments() {
        // 모든 댓글 엔티티를 가져와서 DTO 리스트로 변환
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> commentDTOs = comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(commentDTOs);
    }

//    public ResponseEntity<?> getCommentById(Long commentId) {
//        Optional<Comment> comment = commentRepository.findById(commentId);
//        if (comment.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(comment.get());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found with id " + commentId);
//    }

    public ResponseEntity<?> getCommentsByDiaryId(UUID diaryId) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();
            List<Comment> comments = commentRepository.findByDiary(diary);
            // Comment 엔티티 리스트를 CommentDTO 리스트로 변환
            List<CommentDTO> commentDTOs = comments.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(commentDTOs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
        }
    }

    public ResponseEntity<?> createComment(CommentDTO commentDTO) {
        Comment newComment = new Comment();
        Comment createdComment = this.setEntityData(commentDTO, newComment);
        Comment savedComment = commentRepository.save(createdComment);
        CommentDTO savedCommentDTO = convertToDTO(savedComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentDTO);
    }

    public ResponseEntity<?> addCommentToDiary(UUID diaryId, CommentDTO commentDTO) {
        // 1. 다이어리 조회
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();

            // 2. 댓글 엔티티 생성 및 다이어리와 연관짓기
            Comment comment = new Comment();
            comment.setContent(commentDTO.getContent());
            comment.setDiary(diary); // Diary와 연관 관계 설정

            // 3. 댓글 저장
            Comment savedComment = commentRepository.save(comment);

            // 4. 저장된 댓글을 DTO로 변환
            CommentDTO savedCommentDTO = convertToDTO(savedComment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found with id " + diaryId);
        }
    }

    public ResponseEntity<?> updateComment(UUID commentId, CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            Comment changedComment = this.setEntityData(commentDTO, comment);
            CommentDTO savedCommentDTO = convertToDTO(commentRepository.save(changedComment));
            return ResponseEntity.status(HttpStatus.OK).body(savedCommentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found with id " + commentId);
        }
    }

    public ResponseEntity<?> deleteComment(UUID commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            commentRepository.deleteById(commentId);
            return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found with id " + commentId);
    }

    // Comment 엔티티를 업데이트하는 메서드
    private Comment setEntityData(CommentDTO commentDTO, Comment comment) {
        comment.setContent(commentDTO.getContent());

        return comment;
    }

    // Comment 엔티티를 CommentDTO로 변환하는 메서드
    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(comment.getCreatedAt());
//        commentDTO.setModifiedDate(comment.getModifiedDate());
        return commentDTO;
    }
}
