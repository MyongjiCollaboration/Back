package com.example.demo.service;

import com.example.demo.dto.QuizResponseDto;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private LocalDate lastQuizDate;
    private Quiz lastQuiz;

    public QuizService(QuizRepository quizRepository) {

        this.quizRepository = quizRepository;
        this.lastQuizDate = LocalDate.now().minusDays(1);
    }

    public QuizResponseDto getDailyQuiz() {
        LocalDate currentDate = LocalDate.now();

        // 오늘의 퀴즈가 이미 선택된 경우
        if (lastQuizDate.isEqual(currentDate)) {
            return mapToResponseDto(lastQuiz);
        }

        // 새로운 퀴즈 선택
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty()) {
            return null; // 퀴즈가 없으면 null 반환
        }

        SecureRandom secureRandom = new SecureRandom();
        int random = secureRandom.nextInt(quizzes.size());
        lastQuiz = quizzes.get(random);
        lastQuizDate = currentDate; // 오늘의 날짜로 업데이트

        return mapToResponseDto(lastQuiz);
    }

    private QuizResponseDto mapToResponseDto(Quiz quiz) {
        QuizResponseDto quizResponseDto = new QuizResponseDto();
        quizResponseDto.setId(quiz.getId());
        quizResponseDto.setContent(quiz.getContent());
        return quizResponseDto;
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
