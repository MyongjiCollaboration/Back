package com.example.demo.controller;

import com.example.demo.authentication.user.AuthenticatedUser;
import com.example.demo.dto.response.MyPageResponseData;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.QuizAnswer;
import com.example.demo.dto.QuizAnswerRequestDto;
import com.example.demo.entity.Users;
import com.example.demo.service.QuizAnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDto<QuizAnswerRequestDto>> submitAnswer(@AuthenticatedUser Users user, @RequestBody QuizAnswerRequestDto dto) {
        QuizAnswerRequestDto quizAnswerRequestDto = quizAnswerService.saveQuizAnswer(user, dto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", quizAnswerRequestDto), HttpStatus.OK);
    }
}
