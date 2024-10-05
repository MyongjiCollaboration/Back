package com.example.demo.service;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.QuizAnswer;
import com.example.demo.dto.QuizAnswerRequestDto;
import com.example.demo.entity.Users;
import com.example.demo.repository.QuizAnswerRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;
    private final QuizRepository quizRepository; // QuizRepository 추가
    private final UserRepository userRepository; // UserRepository 추가


    // 생성자에 레포지토리 추가
    public QuizAnswerService(QuizAnswerRepository quizAnswerRepository, QuizRepository quizRepository, UserRepository userRepository) {
        this.quizAnswerRepository = quizAnswerRepository;
        this.quizRepository = quizRepository; // QuizRepository 할당
        this.userRepository = userRepository; // UserRepository 할당
    }

    public String saveQuizAnswer(QuizAnswerRequestDto dto) {
        QuizAnswer quizAnswer = new QuizAnswer();
        UUID quizId = dto.getQuizId();
        //UUID userId = dto.getUserId();

        // 퀴즈 조회
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        // 사용자 조회
        //Optional<Users> userOptional = userRepository.findById(userId);

        // 퀴즈가 존재하지 않을 경우 예외 처리
        if (quizOptional.isEmpty()) {
            throw new RuntimeException("퀴즈를 찾을 수 없습니다. ID: " + quizId);
        }

        // 사용자가 존재하지 않을 경우 예외 처리
//        if (userOptional.isEmpty()) {
//            throw new RuntimeException("사용자를 찾을 수 없습니다. ID: " + userId);
//        }

        // 퀴즈와 사용자 설정
        quizAnswer.setQuiz(quizOptional.get());
     //   quizAnswer.setUser(userOptional.get());
        quizAnswer.setContent(dto.getContent());



        return "성공적으로 답했습니다";
    }

}
