package com.example.demo.service;

import com.example.demo.dto.QuizResponseDto;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizResponseDto getRandomQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty()) {
            return null;
        }
        SecureRandom secureRandom = new SecureRandom();
        int random = secureRandom.nextInt(quizzes.size());
        Quiz quiz = quizzes.get(random);
        QuizResponseDto quizResponseDto = new QuizResponseDto();
        quizResponseDto.setId(quiz.getId());
        quizResponseDto.setContent(quiz.getContent());
        return quizResponseDto;
        //        List<Quiz> quizzes = quizRepository.findAll();
//        if (quizzes.isEmpty()) {
//            return null; // 퀴즈가 없을 경우 null 반환
//        }
//        Collections.shuffle(quizzes);
//
//        return quizzes.get(0);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
