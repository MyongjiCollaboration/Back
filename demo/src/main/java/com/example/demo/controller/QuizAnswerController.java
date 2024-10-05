package com.example.demo.controller;

import com.example.demo.entity.QuizAnswer;
import com.example.demo.dto.QuizAnswerRequestDto;
import com.example.demo.service.QuizAnswerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/quizanswer")
public class QuizAnswerController {
    private final QuizAnswerService quizAnswerService;
    public QuizAnswerController(QuizAnswerService quizAnswerService) {
        this.quizAnswerService = quizAnswerService;
    }

    // 사용자가 답을 입력하고 저장하는 엔드포인트
    @PostMapping("/submit")
    public String submitAnswer(@RequestBody QuizAnswerRequestDto dto) {
        return quizAnswerService.saveQuizAnswer(dto);
    }
}
