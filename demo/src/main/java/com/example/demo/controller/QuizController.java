package com.example.demo.controller;

import com.example.demo.dto.QuizResponseDto;
import com.example.demo.entity.Family;
import com.example.demo.entity.Quiz;
import com.example.demo.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;


    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping()
    public QuizResponseDto getRandomQuiz() {
        return quizService.getDailyQuiz();
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        try {
            // Quiz 저장 (id는 자동 생성)
            Quiz savedQuiz = quizService.saveQuiz(quiz);
            return ResponseEntity.ok(savedQuiz);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create-default")
    public ResponseEntity<Void> createDefaultQuizzes() {
        try {
            quizService.createDefaultQuizzes(); // 자동 퀴즈 생성
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
