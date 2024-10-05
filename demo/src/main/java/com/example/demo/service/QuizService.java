package com.example.demo.service;

import com.example.demo.dto.QuizResponseDto;
import com.example.demo.entity.Family;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
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

    // 자동으로 퀴즈를 생성하는 메소드
    public void createDefaultQuizzes() {
        // 미리 정의된 질문 리스트
        List<String> questions = Arrays.asList(
                "가족이 함께 가고 싶은 여행지는?",
                "엄마가 가장 좋아하는 색은?",
                "부모님이 처음 만난 장소는?",
                "가족 여행에서 가장 기억에 남는 순간은?",
                "어제 저녁 메뉴는?",
                "형이 가장 좋아하는 운동은?",
                "형제가 가장 좋아하는 게임은?",
                "아빠가 가장 좋아하는 음악은?",
                "아빠가 좋아하는 노래는?",
                "누나가 자주 보는 TV 프로그램은?",
                "아빠의 어린 시절 꿈은?",
                "아빠의 취미는?",
                "가족이 함께 만든 특별한 요리는?",
                "가족 중 가장 잘 웃는 사람은?"
        );

        // 각 질문을 Quiz로 저장
        for (String question : questions) {
            Quiz quiz = Quiz.builder()
                    .content(question)
                    .build();
            quizRepository.save(quiz);
        }
    }
}
