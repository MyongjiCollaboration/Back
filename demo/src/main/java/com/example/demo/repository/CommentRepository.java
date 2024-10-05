package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByDiary(Diary diary);
}
